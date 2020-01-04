package com.acorsetti;

import com.acorsetti.core.live.LiveStatsUpdaterService;
import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.eval.Chance;
import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.model.eval.MatchProbability;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.jpa.GoalExpectancyEntity;
import com.acorsetti.core.repository.GoalExpectancyRepository;
import com.acorsetti.core.service.FixtureService;
import com.acorsetti.core.service.MatchProbabilityCalculatorService;
import com.acorsetti.core.service.probabilities.GoalExpectancyCalculatorService;
import com.acorsetti.core.service.probabilities.PoissonCalculatorService;
import com.acorsetti.core.updater.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Autowired
	private BetUpdater betUpdater;

	@Autowired
	private EventUpdater eventUpdater;

	@Autowired
	private FixtureService fixtureService;

	@Autowired
	private MatchProbabilityCalculatorService matchProbabilityCalculatorService;

	@Autowired
	@Qualifier("GoalExpectancyCalculatorServiceWeightedAnalytics")
	private GoalExpectancyCalculatorService goalExpectancyCalculatorService;

	@Autowired
	private GoalExpectancyRepository goalExpectancyRepository;

	@Autowired
	private LiveStatsUpdaterService liveStatsUpdaterService;

	@Autowired
	private PoissonCalculatorService poissonCalculatorService;

	@Override
	public void run(String args[]) {


		//this.eventUpdater.updateCloseGoalEvents();
		//this.rapidUpdateHelper();
		//this.poissonFromMeanToChance();
		this.linearRegression();
		System.exit(0);
		/*
		this.liveStatsUpdater.updateLiveStats();

		/*
		MatchStatistics ms1 = new MatchStatistics(
				new ShotsOnTargetStat(2,8), new ShotsOffTargetStat(1,5),
				new BlockedShotsStat(1,5),new TotalShotsStat(4,17),
				new InboxShotsStat(3,9), new OutboxShotsStat(1,8),
				new KeeperSavesStat(4,1), 	new PossessionStat(23,77, 241,773,154,690),
				new CornerStat(3,6), new OffsideStat(0,2), new DirtyPlayStat(9,6,1,1,0,0)
		);
		ms1.setFixtureId("100");
		MatchStatistics ms2 = new MatchStatistics(
				new ShotsOnTargetStat(8,8), new ShotsOffTargetStat(3,6),
				new BlockedShotsStat(111,5),new TotalShotsStat(3,50),
				new InboxShotsStat(3,9), new OutboxShotsStat(2,10),
				new KeeperSavesStat(4,5), 	new PossessionStat(23,77, 241,773,154,700),
				new CornerStat(3,6), new OffsideStat(0,2), new DirtyPlayStat(10,7,0,1,2,0)
		);
		ms2.setFixtureId("100");
		TimedMatchStatistics tms1 = new TimedMatchStatistics(ms1, 45);
		TimedMatchStatistics tms2 = new TimedMatchStatistics(ms2, 50);
		TimedMatchStatistics diff = new TimedMatchStatisticsDiffCalculator().diff(tms1, tms2);
		System.out.println(diff);
		 */
	}

	@Autowired
	private FixtureUpdater fixtureUpdater;

	@Autowired
	private SeasonUpdater seasonUpdater;

	@Autowired
	private CountryUpdater countryUpdater;

	@Autowired
	private LeagueUpdater leagueUpdater;

	@Autowired
	private TeamUpdater teamUpdater;

	@Autowired
	private StandingUpdater standingUpdater;

	@Autowired
	private GoalExpectancyUpdater goalExpectancyUpdater;

	/**
	 * takes approximatelly 15 minutes...15k api calls
	 */
	private void completeUpdateHelper(){
		this.seasonUpdater.updateSeasons();
		this.countryUpdater.updateCountries();
		this.leagueUpdater.updateLeagues();
		this.teamUpdater.updateAllTeams();
		this.standingUpdater.updateAllStandings();
		this.fixtureUpdater.updateCloseFixtures();
		this.betUpdater.updateResultPicksAndBets();
		this.goalExpectancyUpdater.updateGoalExpectancy();
	}

	/**
	 * takes approximatelly 5 minutes
	 */
	private void rapidUpdateHelper(){
		//this.standingUpdater.updateAllStandings();
		this.fixtureUpdater.updateCloseFixtures();
		this.betUpdater.updateResultPicksAndBets();
		this.goalExpectancyUpdater.updateGoalExpectancy();
		//this.eventUpdater.updateCloseGoalEvents();
	}

	private void poissonFromMeanToChance(){
		int n = 0;
		double[] x = new double[1000];
		double[] y = new double[1000];
		double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;

		int minNumOfGoals = 2; //2 for O1.5

		try{
			String url = "jdbc:mysql://localhost:3306/soccerlab";
			Connection conn = DriverManager.getConnection(url,"admin","admin");
			PreparedStatement statement = conn.prepareStatement("select countryname, leaguename, eventdate, hometeamname, awayteamname,ge_sum \n" +
					"from goal_expectancy_view \n" +
					"where eventdate >= now() and eventdate <= date_add(now(), INTERVAL 5 day)\n" +
					"order by eventdate asc");
			ResultSet rs = statement.executeQuery();
			while (rs.next()){
				String country = rs.getString("countryname");
				String leaguename = rs.getString("leaguename");
				String eventdate = rs.getString("eventdate");
				String hometeamname = rs.getString("hometeamname");
				String awayteamname = rs.getString("awayteamname");
				double ge_sum = rs.getDouble("ge_sum");

				double chanceOfHavingLessThanMinNumOfGoals = 0.0;
				for(int i = 0; i < minNumOfGoals; i++){
					double chanceOfHavingIGoals = this.poissonCalculatorService.poissonFormula(i, ge_sum);
					chanceOfHavingLessThanMinNumOfGoals += chanceOfHavingIGoals;
				}
				double chanceOfHavingMoreThanMinNumOfGoals = 1 - chanceOfHavingLessThanMinNumOfGoals;
				x[n]= ge_sum;
				y[n] = chanceOfHavingMoreThanMinNumOfGoals;
				sumx  += x[n];
				sumx2 += x[n] * x[n];
				sumy  += y[n];
				n++;
				System.out.println(country + " - " + leaguename + " - "+eventdate+" - "+hometeamname+" - "+awayteamname+" - Goal expected: "+ge_sum+" - Over 1.5%: "+chanceOfHavingMoreThanMinNumOfGoals);
			}
			double xbar = sumx / n;
			double ybar = sumy / n;
			double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
			for (int i = 0; i < n; i++) {
				xxbar += (x[i] - xbar) * (x[i] - xbar);
				yybar += (y[i] - ybar) * (y[i] - ybar);
				xybar += (x[i] - xbar) * (y[i] - ybar);
			}
			double beta1 = xybar / xxbar;
			double beta0 = ybar - beta1 * xbar;

			// print results
			System.out.println("y   = " + beta1 + " * x + " + beta0);
		}
		catch (Exception e){
			System.err.println(e.toString());
		}

	}

	private void linearRegression(){
		int n = 0;
		double[] x = new double[1000];
		double[] y = new double[1000];
		double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;

		int minNumOfGoals = 2; //2 for O1.5

		for(double g_e=0.5; g_e<=7.0; g_e = g_e+0.01){
			double chanceOfHavingLessThanMinNumOfGoals = 0.0;
			for(int i = 0; i < minNumOfGoals; i++){
				double chanceOfHavingIGoals = this.poissonCalculatorService.poissonFormula(i, g_e);
				chanceOfHavingLessThanMinNumOfGoals += chanceOfHavingIGoals;
			}
			double chanceOfHavingMoreThanMinNumOfGoals = 1 - chanceOfHavingLessThanMinNumOfGoals;

			System.out.println(g_e+";"+chanceOfHavingMoreThanMinNumOfGoals);
			x[n]= g_e;
			y[n] = chanceOfHavingMoreThanMinNumOfGoals;
			sumx  += x[n];
			sumx2 += x[n] * x[n];
			sumy  += y[n];
			n++;
		}
		double xbar = sumx / n;
		double ybar = sumy / n;
		double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
		for (int i = 0; i < n; i++) {
			xxbar += (x[i] - xbar) * (x[i] - xbar);
			yybar += (y[i] - ybar) * (y[i] - ybar);
			xybar += (x[i] - xbar) * (y[i] - ybar);
		}
		double beta1 = xybar / xxbar;
		double beta0 = ybar - beta1 * xbar;

		// print results
		System.out.println("y   = " + beta1 + " * x + " + beta0);
	}

	private void excel() throws Exception{
		String file = "C:\\Users\\acorsett\\Desktop\\England2017_2019.xlsx";
		String fileNew = "C:\\Users\\acorsett\\Desktop\\England2017_2019_new.xlsx";
		FileInputStream fis=new FileInputStream(new File(file));
		Workbook workbook = new XSSFWorkbook(fis);
		outer:
		for(Sheet sheet: workbook) {
			System.out.println("SHEET: " + workbook.getSheetIndex(sheet));
			for(Row row: sheet){
				if ( row.getRowNum() == 0 ) continue; //skip first row
				System.out.println("ROW: " + row.getRowNum() + " / " + sheet.getPhysicalNumberOfRows());

				Cell leagueIdCell = row.getCell(0);
				Cell homeIdCell = row.getCell(4);
				Cell awayIdCell = row.getCell(5);
				Cell o2_5Cell = row.getCell(13);
				if (o2_5Cell == null) o2_5Cell = row.createCell(13);
				Cell u2_5Cell = row.getCell(14);
				if (u2_5Cell == null) u2_5Cell = row.createCell(14);

				String leagueId;
				try{
					leagueId = String.valueOf(leagueIdCell.getNumericCellValue());
				}
				catch (Exception e){
					System.err.println("e:"+e.toString());
					continue outer;
				}
				if( leagueId.isEmpty() ) continue outer;

				String homeId = String.valueOf(homeIdCell.getNumericCellValue());
				String awayId = String.valueOf(awayIdCell.getNumericCellValue());
				List<Fixture> fixtures = this.fixtureService.byLeagueHomeAndAway(leagueId.substring(0,leagueId.indexOf(".")), homeId.substring(0,homeId.indexOf(".")), awayId.substring(0,awayId.indexOf(".")));
				if(fixtures.isEmpty()){
					System.out.println("Fixtures for L: "+leagueId+" and H: "+homeId+" and A: "+awayId+ " is empty");
					continue;
				}
				if(fixtures.size()>1){
					System.out.println("Fixtures for L: "+leagueId+" and H: "+homeId+" and A: "+awayId+ " > 1!!");
					continue;
				}
				Fixture pivot = fixtures.get(0);
				MatchProbability mp;
				try{
					mp = this.matchProbabilityCalculatorService.calculateProbability(pivot);
				}
				catch (Exception e){
					System.err.println("Exception. continuing.."+e.toString());
					o2_5Cell.setCellValue("N/A");
					u2_5Cell.setCellValue("N/A");
					continue;
				}
				double o2_5c = mp.getMarketChance(MarketValue.O2_5).getValue();
				double u2_5c = mp.getMarketChance(MarketValue.U2_5).getValue();
				System.out.println("Over: " + o2_5c + " - Under. " + u2_5c);
				o2_5Cell.setCellValue(o2_5c);
				u2_5Cell.setCellValue(u2_5c);
			}
		}
		System.out.println("Saving...");
		FileOutputStream out = new FileOutputStream(fileNew);
		workbook.write(out);
		out.close();
	}
}

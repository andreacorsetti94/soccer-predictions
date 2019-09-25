package com.acorsetti;

import com.acorsetti.updater.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Autowired
	private BetUpdater betUpdater;

	@Override
	public void run(String args[]) {
		/*
		List<Fixture> fixtureList = this.fixtureService.fixturesInPeriodByDB(LocalDate.now(), LocalDate.now().plusDays(6));
		List<MatchPick> matchPicks = this.matchPickService.generateNewPicks(fixtureList);
		System.out.println(matchPicks);
		*/
		//this.seasonUpdater.updateSeasons();
		//this.countryUpdater.updateCountries();
		//this.leagueUpdater.updateLeagues();
		//this.teamUpdater.updateAllTeams();
		this.standingUpdater.updateAllStandings();
		//this.fixtureUpdater.updateCloseFixtures();
		//this.betUpdater.updateResultPicksAndBets();
		System.out.println("OVER.");
		System.exit(0);

	}

	/*
	Update complete:
	seasons - countries - leagues - teams - standings - all fixtures - bets
	 */

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

}

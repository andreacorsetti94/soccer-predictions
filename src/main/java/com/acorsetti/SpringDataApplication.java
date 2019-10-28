package com.acorsetti;

import com.acorsetti.core.model.eval.MatchProbability;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.jpa.GoalExpectancyEntity;
import com.acorsetti.core.repository.GoalExpectancyRepository;
import com.acorsetti.core.service.FixtureService;
import com.acorsetti.core.service.MatchProbabilityCalculatorService;
import com.acorsetti.core.updater.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
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

	@Override
	public void run(String args[]) {

		/*
		//this.completeUpdateHelper();
		this.rapidUpdateHelper();
		System.exit(0);
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
	}

	/**
	 * takes approximatelly 5 minutes
	 */
	private void rapidUpdateHelper(){
		//this.standingUpdater.updateAllStandings();
		this.fixtureUpdater.updateCurrentFixtures();
		this.betUpdater.updateResultPicksAndBets();
	}

}

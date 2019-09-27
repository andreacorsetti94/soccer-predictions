package com.acorsetti;

import com.acorsetti.core.updater.*;
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

		this.completeUpdateHelper();
		System.exit(0);

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
		this.standingUpdater.updateAllStandings();
		this.fixtureUpdater.updateCurrentFixtures();
		this.betUpdater.updateResultPicksAndBets();
	}

}

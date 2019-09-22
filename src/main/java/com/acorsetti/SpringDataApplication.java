package com.acorsetti;

import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.model.jpa.MatchPick;
import com.acorsetti.service.FixtureService;
import com.acorsetti.service.MatchPickService;
import com.acorsetti.updater.BetUpdater;
import com.acorsetti.updater.FixtureUpdater;
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
	private MatchPickService matchPickService;

	@Autowired
	private FixtureService fixtureService;

	@Autowired
	private FixtureUpdater fixtureUpdater;

	@Autowired
	private BetUpdater betUpdater;

	@Override
	public void run(String args[]) {
		/*
		List<Fixture> fixtureList = this.fixtureService.fixturesInPeriodByDB(LocalDate.now(), LocalDate.now().plusDays(6));
		List<MatchPick> matchPicks = this.matchPickService.generateNewPicks(fixtureList);
		System.out.println(matchPicks);
		*/
		//this.fixtureUpdater.updateCloseFixtures();
		this.betUpdater.updateResultPicksAndBets();
	}
}

package com.acorsetti;

import com.acorsetti.updater.BetUpdater;
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
		//this.fixtureUpdater.updateCloseFixtures();
		this.betUpdater.updateResultPicksAndBets();

	}

}

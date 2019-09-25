package com.acorsetti.updater.impl;

import com.acorsetti.model.jpa.Bet;
import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.model.jpa.MatchPick;
import com.acorsetti.service.*;
import com.acorsetti.updater.BetUpdater;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Configuration
@Service
@PropertySource("classpath:scheduler.properties")
public class BetUpdaterImpl implements BetUpdater {
    private static final Logger logger = Logger.getLogger(BetUpdaterImpl.class);

    @Autowired
    private BetService betService;

    @Autowired
    private MatchPickService matchPickService;

    @Autowired
    private BetGeneratorService betGeneratorService;

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private Environment environment;

    @Override
    @Scheduled(cron = "${cron.updateBets}")
    public void updateResultPicksAndBets(){
        logger.info("Bets update started...");
        List<MatchPick> matchPicks = this.updatePickResults();
        logger.info("MatchPick Results updated. Picks: " + matchPicks);

        List<Bet> updatedBets = this.updateOpenBets();
        logger.info("Bets profit updated. Bets: ");

        List<MatchPick> generatedPicks = this.computeNewMatchPicks();
        logger.info("New match picks generated. Picks generated: " );

        List<Bet> betsPlaced = this.placeNewBets(this.matchPickService.openValuablePicks());
        logger.info("New bets placed: " + betsPlaced);

        logger.info("Bets update finished...");

    }

    private List<MatchPick> updatePickResults(){
        List<MatchPick> openMatchPicks = this.matchPickService.openPicks();
        return this.matchPickService.updateMatchPicksResult(openMatchPicks);
    }

    private List<Bet> updateOpenBets(){
        List<Bet> openBets = this.betService.openBets();
        return this.betService.updateBetsProfit(openBets);
    }

    private List<MatchPick> computeNewMatchPicks(){
        int nextDays = Integer.parseInt(Objects.requireNonNull(environment.getProperty("nextDays")));
        LocalDate now = LocalDate.now();
        List<Fixture> fixtureList = this.fixtureService.fixturesInPeriodByDB(now, now.plusDays(nextDays));

        return this.matchPickService.generateNewPicks(fixtureList);
    }

    private List<Bet> placeNewBets(List<MatchPick> valuablePicks){
        List<Bet> betsPlaced = this.betGeneratorService.generateBets(valuablePicks);
        this.betService.saveBets(betsPlaced);
        return betsPlaced;
    }
}

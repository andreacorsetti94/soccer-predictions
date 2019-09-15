package com.acorsetti.updater.impl;

import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.enums.PickResult;
import com.acorsetti.model.jpa.Bet;
import com.acorsetti.model.jpa.MatchPick;
import com.acorsetti.repository.BetRepository;
import com.acorsetti.service.BetGeneratorService;
import com.acorsetti.service.BetService;
import com.acorsetti.service.MatchPickService;
import com.acorsetti.updater.BetUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Configuration
@Service
@PropertySource("classpath:scheduler.properties")
public class BetUpdaterImpl implements BetUpdater {

    @Autowired
    private BetService betService;

    @Autowired
    private MatchPickService matchPickService;

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private BetGeneratorService betGeneratorService;

    @Override
    @Scheduled(cron="${cron.updateOpenBets}")
    public void updateOpenBets() {
        List<Bet> openBets = this.betService.openBets();
        openBets.forEach( bet -> {
            String fixtureId = bet.getFixtureId();
            double betAmount = bet.getAmount();
            double oddValue = bet.getOdds().getValue();
            MarketValue mv = bet.getMarket();

            MatchPick matchPick = this.matchPickService.byFixtureAndMarket(fixtureId, mv);
            PickResult pickResult = matchPick.getPickResult();
            double profit;
            if ( pickResult == null || pickResult == PickResult.TO_BE_DEFINED ) return;
            else if ( pickResult == PickResult.YES ){
                profit = Math.max(0, betAmount * (oddValue - 1) );
            }
            else if( pickResult == PickResult.NO ){
                profit = betAmount * -1;
            }
            else return;
            bet.setProfit(profit);
            this.betRepository.save(bet);
        });
    }

    @Override
    @Scheduled(cron="${cron.placeNewBets}")
    public void placeNewBets() {
        List<MatchPick> valuablePicks = this.matchPickService.openValuablePicks();
        List<Bet> bets = this.betGeneratorService.generateBets(valuablePicks);
        this.betService.saveBets(bets);
    }
}

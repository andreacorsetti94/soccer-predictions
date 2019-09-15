package com.acorsetti.updater.impl;

import com.acorsetti.api.APIOddsRetriever;
import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.enums.PickResult;
import com.acorsetti.model.eval.Chance;
import com.acorsetti.model.eval.MatchProbability;
import com.acorsetti.model.eval.PickValue;
import com.acorsetti.model.jpa.Bet;
import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.model.jpa.MatchPick;
import com.acorsetti.model.odds.FixtureOdds;
import com.acorsetti.model.odds.OddsValue;
import com.acorsetti.repository.BetRepository;
import com.acorsetti.repository.MatchPickRepository;
import com.acorsetti.service.*;
import com.acorsetti.updater.BetUpdater;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private BetRepository betRepository;

    @Autowired
    private BetGeneratorService betGeneratorService;

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private PickResultExtracterService pickResultExtracterService;

    @Autowired
    private MatchPickRepository matchPickRepository;

    @Autowired
    private APIOddsRetriever apiOddsRetriever;

    @Autowired
    private MatchProbabilityCalculatorService matchProbabilityCalculatorService;

    @Autowired
    private PickValueCalculatorService pickValueCalculatorService;

    @Override
    @Scheduled(cron = "${cron.updateBets}")
    public void updateResultPicksAndBets(){
        this.updatePickResults();
        this.updateOpenBets();
        this.computeNewMatchPicks();
        this.placeNewBets();
    }

    private void updatePickResults(){
        List<MatchPick> openMatchPicks = this.matchPickService.openPicks();
        openMatchPicks.forEach( matchPick -> {
            String fixtureId = matchPick.getFixtureId();
            Fixture fixture = this.fixtureService.byId(fixtureId);
            MarketValue mv = matchPick.getMarket();
            PickResult pickResult = matchPick.getPickResult();
            if ( pickResult == null || pickResult == PickResult.TO_BE_DEFINED ){
                pickResult = this.pickResultExtracterService.pickResult(fixture, mv);
            }
            matchPick.setPickResult(pickResult);
        });
        this.matchPickService.savePicks(openMatchPicks);
    }

    private void updateOpenBets(){
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

    private void computeNewMatchPicks(){
        int nextDays = Integer.parseInt("${nextDays}");
        LocalDate now = LocalDate.now();
        List<Fixture> fixtureList = this.fixtureService.fixturesInPeriodByDB(now, now.plusDays(nextDays));

        List<MatchPick> matchPicks = new ArrayList<>();
        fixtureList.forEach( fixture -> {
            String id = fixture.getFixtureId();
            List<FixtureOdds> fixtureOdds = this.apiOddsRetriever.oddsByFixture(id).getBody();
            fixtureOdds.forEach( fOdd -> {
                MatchProbability matchProbability = this.matchProbabilityCalculatorService.calculateProbability(fixture);

                fOdd.getMarketOdds().forEach( marketOdds -> {
                    MarketValue mv = marketOdds.getMarketValue();
                    Chance chance = matchProbability.getMarketChance(mv);
                    OddsValue oddsValue = marketOdds.getOddsValue();
                    PickValue pickValue = this.pickValueCalculatorService.calculatePickValue(chance, oddsValue);
                    MatchPick matchPick = new MatchPick(id, mv, oddsValue, chance, pickValue);
                    matchPicks.add(matchPick);
                });
            });
        });

        List<MatchPick> alreadyPresentPicks = this.matchPickRepository.findAll();
        List<MatchPick> toCreate = new ArrayList<>();
        matchPicks.forEach( matchPick -> {
            if ( ! alreadyPresentPicks.contains(matchPick) ) toCreate.add(matchPick);
        });
        this.matchPickRepository.saveAll(toCreate);
    }

    private void placeNewBets(){
        List<MatchPick> valuablePicks = this.matchPickService.openValuablePicks();
        List<Bet> bets = this.betGeneratorService.generateBets(valuablePicks);
        this.betService.saveBets(bets);
    }
}

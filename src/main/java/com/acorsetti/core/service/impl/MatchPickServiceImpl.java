package com.acorsetti.core.service.impl;

import com.acorsetti.core.api.APIOddsRetriever;
import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.enums.PickResult;
import com.acorsetti.core.model.eval.Chance;
import com.acorsetti.core.model.eval.MatchProbability;
import com.acorsetti.core.model.eval.PickValue;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.jpa.MatchPick;
import com.acorsetti.core.model.odds.FixtureOdds;
import com.acorsetti.core.model.odds.OddsValue;
import com.acorsetti.core.repository.MatchPickRepository;
import com.acorsetti.core.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MatchPickServiceImpl implements MatchPickService {
    private static final Logger logger = Logger.getLogger(MatchPickServiceImpl.class);

    @Autowired
    private MatchPickRepository matchPickRepository;

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private PickResultExtracterService pickResultExtracterService;

    @Autowired
    private APIOddsRetriever apiOddsRetriever;

    @Autowired
    private MatchProbabilityCalculatorService matchProbabilityCalculatorService;

    @Autowired
    private PickValueCalculatorService pickValueCalculatorService;

    public List<MatchPick> listAllPicks(){
        return this.matchPickRepository.findAll();
    }

    public MatchPick byFixtureAndMarket(String fixtureId, MarketValue marketValue){
        return this.matchPickRepository.findByFixtureIdAndMarket(fixtureId, marketValue);
    }

    public List<MatchPick> picksWithOddsBetween(double lowerBound, double upperBound){
        return this.matchPickRepository.findByOddsBetween(new OddsValue(lowerBound),new OddsValue(upperBound));
    }

    public List<MatchPick> openPicks(){
        return this.matchPickRepository.findOpenPicks();
    }

    public List<MatchPick> openValuablePicks(){
        return this.matchPickRepository.findValuableOpenPicks();
    }


    @Override
    public void savePicks(List<MatchPick> matchPicks) {
        this.matchPickRepository.saveAll(matchPicks);
    }

    @Override
    public List<MatchPick> updateMatchPicksResult(List<MatchPick> matchPicks) {
        matchPicks.forEach( matchPick -> {
            String fixtureId = matchPick.getFixtureId();
            Fixture fixture = this.fixtureService.byId(fixtureId);
            MarketValue mv = matchPick.getMarket();
            PickResult pickResult = matchPick.getPickResult();
            if ( pickResult == null || pickResult == PickResult.TO_BE_DEFINED ){
                pickResult = this.pickResultExtracterService.pickResult(fixture, mv);
            }
            matchPick.setPickResult(pickResult);
        });
        this.savePicks(matchPicks);
        return matchPicks;
    }

    @Override
    public List<MatchPick> generateNewPicks(List<Fixture> fixtureList) {
        List<MatchPick> alreadyPresentPicks = this.matchPickRepository.findAll();
        List<String> fixturesAnalyzed = new ArrayList<>();
        alreadyPresentPicks.forEach( pick -> fixturesAnalyzed.add(pick.getFixtureId()));

        List<MatchPick> matchPicks = new ArrayList<>();
        fixtureList.forEach( fixture -> {
            if ( fixturesAnalyzed.contains(fixture.getFixtureId()) ) return;
            String id = fixture.getFixtureId();
            List<FixtureOdds> fixtureOdds = this.apiOddsRetriever.oddsByFixture(id).getBody();
            fixtureOdds.forEach( fOdd -> {
                MatchProbability matchProbability;
                try{
                    matchProbability = this.matchProbabilityCalculatorService.calculateProbability(fixture);
                }
                catch (Exception e){
                    logger.warn("Catched exception: " + e.toString() + " while calculating probability" +
                            " for fixture: " + fixture);
                    return;
                }
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
        if ( matchPicks.isEmpty() ) return Collections.emptyList();

        this.matchPickRepository.saveAll(matchPicks);
        return matchPicks;
    }

}

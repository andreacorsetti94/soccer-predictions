package com.acorsetti.service.impl;

import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.jpa.MatchPick;
import com.acorsetti.model.odds.OddsValue;
import com.acorsetti.repository.MatchPickRepository;
import com.acorsetti.service.MatchPickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchPickServiceImpl implements MatchPickService {

    @Autowired
    private MatchPickRepository matchPickRepository;

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
}

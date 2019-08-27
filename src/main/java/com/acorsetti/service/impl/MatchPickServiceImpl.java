package com.acorsetti.service.impl;

import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.MatchPick;
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
        return this.matchPickRepository.findByOddsBetween(lowerBound,upperBound);
    }

    public List<MatchPick> openPicks(){
        return this.matchPickRepository.findOpenPicks();
    }

    public List<MatchPick> openValuablePicks(){
        return this.matchPickRepository.findValuableOpenPicks();
    }
}

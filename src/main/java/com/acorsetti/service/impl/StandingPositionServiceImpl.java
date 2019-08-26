package com.acorsetti.service.impl;

import com.acorsetti.model.StandingPosition;
import com.acorsetti.repository.StandingPositionRepository;
import com.acorsetti.service.StandingPositionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StandingPositionServiceImpl implements StandingPositionService {

    @Autowired
    private StandingPositionRepository standingPositionRepository;

    public List<StandingPosition> byLeague(String leagueId){
        return this.standingPositionRepository.findByLeagueIdOrderByRankAsc(leagueId);
    }
}

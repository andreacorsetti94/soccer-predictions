package com.acorsetti.core.service.impl;

import com.acorsetti.core.model.jpa.StandingPosition;
import com.acorsetti.core.repository.StandingPositionRepository;
import com.acorsetti.core.service.StandingPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandingPositionServiceImpl implements StandingPositionService {

    @Autowired
    private StandingPositionRepository standingPositionRepository;

    public List<StandingPosition> byLeague(String leagueId){
        return this.standingPositionRepository.findByLeagueIdOrderByPositionAsc(leagueId);
    }
}

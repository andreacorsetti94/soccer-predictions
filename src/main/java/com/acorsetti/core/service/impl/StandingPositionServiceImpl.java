package com.acorsetti.core.service.impl;

import com.acorsetti.core.model.jpa.StandingPosition;
import com.acorsetti.core.repository.StandingPositionRepository;
import com.acorsetti.core.service.StandingPositionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandingPositionServiceImpl implements StandingPositionService {

    private static final Logger logger = Logger.getLogger(StandingPositionServiceImpl.class);

    @Autowired
    private StandingPositionRepository standingPositionRepository;

    public List<StandingPosition> byLeague(String leagueId){
        return this.standingPositionRepository.findByLeagueIdOrderByPositionAsc(leagueId);
    }

    @Override
    public StandingPosition getStandingPositionByRank(String leagueId, int rank) {
        List<StandingPosition> standingPositions = this.standingPositionRepository.findByLeagueIdOrderByPositionAsc(leagueId);
        for(StandingPosition standingPosition: standingPositions){
            if ( standingPosition.getPosition() == rank ) return standingPosition;
        }
        logger.warn("No Standing position retrieved with leagueId: " + leagueId + " and rank: " + rank);
        return null;
    }

    @Override
    public StandingPosition getStandingPositionByTeamName(String leagueId, String teamName) {
        List<StandingPosition> standingPositions = this.standingPositionRepository.findByLeagueIdOrderByPositionAsc(leagueId);
        for(StandingPosition standingPosition: standingPositions){
            if ( standingPosition.getTeamName().equals(teamName) ) return standingPosition;
        }
        logger.warn("No Standing position retrieved with leagueId: " + leagueId + " and teamName: " + teamName);
        return null;
    }
}

package com.acorsetti.core.service.impl;

import com.acorsetti.core.model.jpa.League;
import com.acorsetti.core.repository.LeagueRepository;
import com.acorsetti.core.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private LeagueRepository leagueRepository;

    public List<League> listLeagues(){
        return this.leagueRepository.findAll();
    }

    public League byId(String id){
        return this.leagueRepository.findByLeagueId(id);
    }

}

package com.acorsetti.service.impl;

import com.acorsetti.model.League;
import com.acorsetti.repository.LeagueRepository;
import com.acorsetti.service.LeagueService;
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

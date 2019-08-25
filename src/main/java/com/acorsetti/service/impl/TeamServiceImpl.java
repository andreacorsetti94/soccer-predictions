package com.acorsetti.service.impl;

import com.acorsetti.model.Team;
import com.acorsetti.repository.TeamRepository;
import com.acorsetti.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> listAllTeams(){
        List<Team> teams = new ArrayList<>();
        this.teamRepository.findAll().forEach(teams::add);
        return teams;
    }

    public Team byId(String id){
        return this.teamRepository.findByTeamId(id);
    }
}

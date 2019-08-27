package com.acorsetti.service;

import com.acorsetti.model.jpa.Team;

import java.util.List;

public interface TeamService {
    List<Team> listAllTeams();
    Team byId(String id);
}

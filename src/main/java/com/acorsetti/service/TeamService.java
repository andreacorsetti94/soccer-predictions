package com.acorsetti.service;

import com.acorsetti.model.Team;

import java.util.List;

public interface TeamService {
    List<Team> listAllTeams();
    Team byId(String id);
}

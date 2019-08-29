package com.acorsetti.service;

import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.model.jpa.Team;

import java.util.List;

public interface TeamService {
    List<Team> listAllTeams();
    Team byId(String id);

    double avgGoalsScored(String teamId, List<Fixture> fixtures);
    double avgGoalsConceived(String teamId, List<Fixture> fixtures);
}

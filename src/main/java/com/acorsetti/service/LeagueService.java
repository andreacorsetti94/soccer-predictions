package com.acorsetti.service;

import com.acorsetti.model.jpa.League;

import java.util.List;

public interface LeagueService {
    List<League> listLeagues();
    League byId(String id);
}

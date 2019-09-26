package com.acorsetti.core.service;

import com.acorsetti.core.model.jpa.League;

import java.util.List;

public interface LeagueService {
    List<League> listLeagues();
    League byId(String id);
}

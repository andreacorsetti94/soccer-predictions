package com.acorsetti.core.service;

import com.acorsetti.core.model.jpa.Season;

import java.util.List;

public interface SeasonService {

    List<Season> allSeasons();
    Season byYear(String year);
}

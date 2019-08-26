package com.acorsetti.service;

import com.acorsetti.model.Season;

import java.util.List;

public interface SeasonService {

    List<Season> allSeasons();
    Season byYear(String year);
}

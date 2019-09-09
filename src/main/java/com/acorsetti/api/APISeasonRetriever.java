package com.acorsetti.api;

import com.acorsetti.model.jpa.Season;

public interface APISeasonRetriever {
    APIResponse<Season> allSeasons();
}

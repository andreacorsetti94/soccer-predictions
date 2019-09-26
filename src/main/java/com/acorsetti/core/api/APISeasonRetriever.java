package com.acorsetti.core.api;

import com.acorsetti.core.model.jpa.Season;

public interface APISeasonRetriever {
    APIResponse<Season> allSeasons();
}

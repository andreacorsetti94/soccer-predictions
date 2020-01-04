package com.acorsetti.core.api;

import com.acorsetti.core.api.APIResponse;
import com.acorsetti.core.live.MatchStatistics;

public interface APILiveMatchStatRetriever {
    APIResponse<MatchStatistics> statsByFixtureId(String fixtureId);
}

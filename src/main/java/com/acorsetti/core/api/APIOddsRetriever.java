package com.acorsetti.core.api;

import com.acorsetti.core.model.odds.FixtureOdds;

public interface APIOddsRetriever {
    APIResponse<FixtureOdds> oddsByFixture(String fixtureId);
}

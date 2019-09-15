package com.acorsetti.api;

import com.acorsetti.model.odds.FixtureOdds;

public interface APIOddsRetriever {
    APIResponse<FixtureOdds> oddsByFixture(String fixtureId);
}

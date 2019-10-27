package com.acorsetti.core.api;

import com.acorsetti.core.model.jpa.Event;

public interface APIEventRetriever {
    APIResponse<Event> byFixtureId(String fixtureId);
}

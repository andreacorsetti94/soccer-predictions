package com.acorsetti.api;

import com.acorsetti.model.jpa.Fixture;

import java.time.LocalDate;

public interface APIFixtureRetriever {
    APIResponse<Fixture> fixturesByDay(LocalDate localDate);
}

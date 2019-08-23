package com.acorsetti.service;

import com.acorsetti.model.Fixture;

import java.util.List;

public interface FixtureService {

    List<Fixture> fixturesByDay(String dayLike);
}

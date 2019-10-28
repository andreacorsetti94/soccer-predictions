package com.acorsetti.core.repository;

import com.acorsetti.core.model.jpa.GoalExpectancyEntity;
import com.acorsetti.core.model.keys.GoalExpectedEntityKey;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GoalExpectancyRepository extends PagingAndSortingRepository<GoalExpectancyEntity, GoalExpectedEntityKey> {

    GoalExpectancyEntity findByFixtureIdAndCalculator(String fixtureId, String calculator);
}

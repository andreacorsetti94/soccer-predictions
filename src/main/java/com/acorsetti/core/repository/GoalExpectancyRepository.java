package com.acorsetti.core.repository;

import com.acorsetti.core.model.jpa.GoalExpectancyEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GoalExpectancyRepository extends PagingAndSortingRepository<GoalExpectancyEntity, String> {

    GoalExpectancyEntity findByFixtureId(String fixtureId);
}

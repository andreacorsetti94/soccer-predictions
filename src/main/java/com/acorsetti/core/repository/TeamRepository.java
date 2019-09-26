package com.acorsetti.core.repository;

import com.acorsetti.core.model.jpa.Team;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TeamRepository extends PagingAndSortingRepository<Team,String> {

    Team findByTeamId(String teamId);

    Iterable<Team> findAll();

}

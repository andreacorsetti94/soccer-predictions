package com.acorsetti.repository;

import com.acorsetti.model.jpa.Team;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TeamRepository extends PagingAndSortingRepository<Team,String> {

    Team findByTeamId(String teamId);

    Iterable<Team> findAll();

}

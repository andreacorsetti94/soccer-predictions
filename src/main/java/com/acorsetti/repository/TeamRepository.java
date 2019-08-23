package com.acorsetti.repository;

import com.acorsetti.model.Team;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TeamRepository extends PagingAndSortingRepository<Team,String> {

    List<Team> findByTeamId(String teamId);

    Iterable<Team> findAll();

}

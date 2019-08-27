package com.acorsetti.repository;

import com.acorsetti.model.jpa.League;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LeagueRepository extends PagingAndSortingRepository<League,String> {

    List<League> findAll();
    League findByLeagueId(String id);

}

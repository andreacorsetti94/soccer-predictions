package com.acorsetti.repository;

import com.acorsetti.model.LeagueTeam;
import com.acorsetti.model.keys.LeagueTeamCompositeKey;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LeagueTeamRepository extends PagingAndSortingRepository<LeagueTeam, LeagueTeamCompositeKey> {

    List<LeagueTeam> findByLeagueId(String leagueId);
}

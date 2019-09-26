package com.acorsetti.core.repository;

import com.acorsetti.core.model.jpa.LeagueTeam;
import com.acorsetti.core.model.keys.LeagueTeamCompositeKey;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LeagueTeamRepository extends PagingAndSortingRepository<LeagueTeam, LeagueTeamCompositeKey> {

    List<LeagueTeam> findByLeagueId(String leagueId);
}

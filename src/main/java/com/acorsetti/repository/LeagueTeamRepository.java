package com.acorsetti.repository;

import com.acorsetti.model.LeagueTeam;
import com.acorsetti.model.Team;
import com.acorsetti.model.keys.LeagueTeamCompositeKey;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LeagueTeamRepository extends PagingAndSortingRepository<LeagueTeam, LeagueTeamCompositeKey> {

    List<Team> findByLeagueId(String leagueId);
}

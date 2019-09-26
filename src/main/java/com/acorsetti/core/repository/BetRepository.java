package com.acorsetti.core.repository;

import com.acorsetti.core.model.jpa.Bet;
import com.acorsetti.core.model.keys.BetCompositeKey;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BetRepository extends PagingAndSortingRepository<Bet, BetCompositeKey> {
    Iterable<Bet> findAll();

    List<Bet> findByProfitIsNull();

    List<Bet> findByAlgoIdAndFixtureId(String algoId, String fixtureId);
}

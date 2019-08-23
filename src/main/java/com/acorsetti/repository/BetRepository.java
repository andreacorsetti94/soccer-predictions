package com.acorsetti.repository;

import com.acorsetti.model.Bet;
import com.acorsetti.model.keys.BetCompositeKey;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BetRepository extends PagingAndSortingRepository<Bet, BetCompositeKey> {
    Iterable<Bet> findAll();

    List<Bet> findByProfitIsNull();

    List<Bet> findByAlgoIdAndFixtureId(String algoId, String fixtureId);
}

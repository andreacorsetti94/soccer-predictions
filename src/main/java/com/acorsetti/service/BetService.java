package com.acorsetti.service;

import com.acorsetti.model.jpa.Bet;

import java.util.List;

public interface BetService {

    boolean hasAlgoAlreadyPlacedBetOnFixture(String algoId, String fixtureId);
    List<Bet> openBets();
    void saveBets(List<Bet> bets);
}

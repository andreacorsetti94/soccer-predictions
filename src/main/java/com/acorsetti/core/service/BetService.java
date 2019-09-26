package com.acorsetti.core.service;

import com.acorsetti.core.model.jpa.Bet;

import java.util.List;

public interface BetService {

    boolean hasAlgoAlreadyPlacedBetOnFixture(String algoId, String fixtureId);
    List<Bet> openBets();
    void saveBets(List<Bet> bets);
    List<Bet> updateBetsProfit(List<Bet> bets);
}

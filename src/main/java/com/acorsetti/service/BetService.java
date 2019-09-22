package com.acorsetti.service;

import com.acorsetti.model.jpa.Bet;
import com.acorsetti.model.jpa.MatchPick;

import java.util.List;

public interface BetService {

    boolean hasAlgoAlreadyPlacedBetOnFixture(String algoId, String fixtureId);
    List<Bet> openBets();
    void saveBets(List<Bet> bets);
    List<Bet> updateBetsProfit(List<Bet> bets);
}

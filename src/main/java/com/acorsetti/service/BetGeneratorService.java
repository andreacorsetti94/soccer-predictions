package com.acorsetti.service;

import com.acorsetti.model.jpa.Bet;
import com.acorsetti.model.jpa.MatchPick;

import java.util.List;

public interface BetGeneratorService {

    List<Bet> generateBets(List<MatchPick> matchPicks);
}

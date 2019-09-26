package com.acorsetti.core.service;

import com.acorsetti.core.model.jpa.Bet;
import com.acorsetti.core.model.jpa.MatchPick;

import java.util.List;

public interface BetGeneratorService {
    List<Bet> generateBets(List<MatchPick> matchPicks);
}

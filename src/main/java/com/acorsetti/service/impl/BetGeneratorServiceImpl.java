package com.acorsetti.service.impl;

import com.acorsetti.model.jpa.Bet;
import com.acorsetti.model.jpa.MatchPick;
import com.acorsetti.service.BetGeneratorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetGeneratorServiceImpl implements BetGeneratorService {

    @Override
    public List<Bet> generateBets(List<MatchPick> matchPicks) {
        //TODO
        return null;
    }

}

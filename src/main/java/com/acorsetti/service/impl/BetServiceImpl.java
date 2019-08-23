package com.acorsetti.service.impl;

import com.acorsetti.model.Bet;
import com.acorsetti.repository.BetRepository;
import com.acorsetti.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetServiceImpl implements BetService {

    @Autowired
    private BetRepository betRepository;

    public boolean hasAlgoAlreadyPlacedBetOnFixture(String algoId, String fixtureId){

        return  !  this.betRepository.findByAlgoIdAndFixtureId(algoId,fixtureId).isEmpty();

    }

    public List<Bet> openBets(){
        return this.betRepository.findByProfitIsNull();
    }
}

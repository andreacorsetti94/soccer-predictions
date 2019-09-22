package com.acorsetti.service.impl;

import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.enums.PickResult;
import com.acorsetti.model.jpa.Bet;
import com.acorsetti.model.jpa.MatchPick;
import com.acorsetti.repository.BetRepository;
import com.acorsetti.service.BetService;
import com.acorsetti.service.MatchPickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetServiceImpl implements BetService {

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private MatchPickService matchPickService;

    public boolean hasAlgoAlreadyPlacedBetOnFixture(String algoId, String fixtureId){

        return  !  this.betRepository.findByAlgoIdAndFixtureId(algoId,fixtureId).isEmpty();

    }

    public List<Bet> openBets(){
        return this.betRepository.findByProfitIsNull();
    }

    @Override
    public void saveBets(List<Bet> bets) {
        this.betRepository.saveAll(bets);
    }

    @Override
    public List<Bet> updateBetsProfit(List<Bet> bets) {
        bets.forEach( bet -> {
            String fixtureId = bet.getFixtureId();
            double betAmount = bet.getAmount();
            double oddValue = bet.getOdds().getValue();
            MarketValue mv = bet.getMarket();

            MatchPick matchPick = this.matchPickService.byFixtureAndMarket(fixtureId, mv);
            PickResult pickResult = matchPick.getPickResult();
            double profit;
            if ( pickResult == null || pickResult == PickResult.TO_BE_DEFINED ) return;
            else if ( pickResult == PickResult.YES ){
                profit = Math.max(0, betAmount * (oddValue - 1) );
            }
            else if( pickResult == PickResult.NO ){
                profit = betAmount * -1;
            }
            else return;
            bet.setProfit(profit);
            this.betRepository.save(bet);
        });
        return bets;
    }
}

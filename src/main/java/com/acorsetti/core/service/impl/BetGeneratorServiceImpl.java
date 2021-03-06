package com.acorsetti.core.service.impl;

import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.jpa.Algorithm;
import com.acorsetti.core.model.jpa.Bet;
import com.acorsetti.core.model.jpa.MatchPick;
import com.acorsetti.core.service.AlgorithmService;
import com.acorsetti.core.service.BetGeneratorService;
import com.acorsetti.core.service.BetService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@PropertySource(value={"classpath:application.properties"})
public class BetGeneratorServiceImpl implements BetGeneratorService {

    private static final Logger logger = Logger.getLogger(BetGeneratorServiceImpl.class);

    @Value("${bet.placing.oddsLowerBound}")
    private double oddsLowerBound;

    @Value("${bet.placing.oddsUpperBound}")
    private double oddsUpperBound;

    @Value("${bet.algo.numOfBetsLowerBound}")
    private int numOfBetsLowerBound;

    @Value("${bet.algo.numOfBetsUpperBound}")
    private int numOfBetsUpperBound;

    @Autowired
    private AlgorithmService algorithmService;

    @Autowired
    private BetService betService;

    @Override
    public List<Bet> generateBets(List<MatchPick> matchPicks) {
        if ( matchPicks == null || matchPicks.isEmpty() ) return Collections.emptyList();

        List<Bet> bets = new ArrayList<>();

        List<Algorithm> algorithms = this.algorithmService.listAllAlgorithms();
        for(int i = 0; i < algorithms.size(); i++){
            Algorithm algorithm = algorithms.get(i);
            String algoId = algorithm.getId();
            int betsToPlace = new Random().nextInt(numOfBetsUpperBound) + numOfBetsLowerBound;

            List<MatchPick> picksForAlgo = randomPicks(matchPicks, betsToPlace);
            for(MatchPick matchPick: picksForAlgo){
                String fixtureId = matchPick.getFixtureId();
                MarketValue marketValue = matchPick.getMarket();

                if ( this.betService.hasAlgoAlreadyPlacedBetOnFixture(algoId, fixtureId) ) continue;

                double oddsValue = matchPick.getOdds().getValue();
                if ( oddsValue >= oddsLowerBound && oddsValue <= oddsUpperBound ){
                    Bet bet = new Bet(algoId, fixtureId, marketValue, randomAmount(), matchPick.getOdds(), null);
                    bets.add(bet);
                }
            }
            logger.info("Bets placed for algorithm n# " + i + ". Progress: " + i + " / " + algorithms.size());
        }
        return bets;
    }

    private static double randomAmount() {
        int[] amounts = {20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90};
        int size = amounts.length;

        return amounts[new Random().nextInt(size)];
    }

    private static List<MatchPick> randomPicks(List<MatchPick> matchPickList, int numOfPicks) {
        int listSize = matchPickList.size();
        if (numOfPicks >= listSize) return matchPickList;

        List<MatchPick> chosenPicks = new ArrayList<>();
        int i = 0;
        while (i < numOfPicks) {
            int listIndexToChoose = new Random().nextInt(listSize);
            MatchPick matchPick = matchPickList.get(listIndexToChoose);
            if (!chosenPicks.contains(matchPick)) {
                chosenPicks.add(matchPick);
                i++;
            }
        }

        return chosenPicks;
    }
}

package com.acorsetti.updater.impl;

import com.acorsetti.api.APIOddsRetriever;
import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.eval.Chance;
import com.acorsetti.model.eval.MatchProbability;
import com.acorsetti.model.eval.PickValue;
import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.model.jpa.MatchPick;
import com.acorsetti.model.odds.FixtureOdds;
import com.acorsetti.model.odds.OddsValue;
import com.acorsetti.repository.MatchPickRepository;
import com.acorsetti.service.FixtureService;
import com.acorsetti.service.MatchProbabilityCalculatorService;
import com.acorsetti.service.PickValueCalculatorService;
import com.acorsetti.updater.MatchPickUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Service
@PropertySource("classpath:scheduler.properties")
public class MatchPickUpdaterImpl implements MatchPickUpdater {

    @Autowired
    private MatchPickRepository matchPickRepository;

    @Autowired
    private APIOddsRetriever apiOddsRetriever;

    @Autowired
    private MatchProbabilityCalculatorService matchProbabilityCalculatorService;

    @Autowired
    private PickValueCalculatorService pickValueCalculatorService;

    @Autowired
    private FixtureService fixtureService;

    @Override
    @Scheduled(cron = "${cron.updatePicks}")
    public void newMatchPicks() {
        int nextDays = Integer.parseInt("${nextDays}");
        LocalDate now = LocalDate.now();
        List<Fixture> fixtureList = this.fixtureService.fixturesInPeriodByDB(now, now.plusDays(nextDays));

        List<MatchPick> matchPicks = new ArrayList<>();
        fixtureList.forEach( fixture -> {
            String id = fixture.getFixtureId();
            List<FixtureOdds> fixtureOdds = this.apiOddsRetriever.oddsByFixture(id).getBody();
            fixtureOdds.forEach( fOdd -> {
                MatchProbability matchProbability = this.matchProbabilityCalculatorService.calculateProbability(fixture);

                fOdd.getMarketOdds().forEach( marketOdds -> {
                    MarketValue mv = marketOdds.getMarketValue();
                    Chance chance = matchProbability.getMarketChance(mv);
                    OddsValue oddsValue = marketOdds.getOddsValue();
                    PickValue pickValue = this.pickValueCalculatorService.calculatePickValue(chance, oddsValue);
                    MatchPick matchPick = new MatchPick(id, mv, oddsValue, chance, pickValue);
                    matchPicks.add(matchPick);
                });
            });
        });

        List<MatchPick> alreadyPresentPicks = this.matchPickRepository.findAll();
        List<MatchPick> toCreate = new ArrayList<>();
        matchPicks.forEach( matchPick -> {
            if ( ! alreadyPresentPicks.contains(matchPick) ) toCreate.add(matchPick);
        });
        this.matchPickRepository.saveAll(toCreate);
    }
}

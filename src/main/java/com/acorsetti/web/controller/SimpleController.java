package com.acorsetti.web.controller;

import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.jpa.League;
import com.acorsetti.core.model.jpa.MatchPick;
import com.acorsetti.core.service.FixtureService;
import com.acorsetti.core.service.LeagueService;
import com.acorsetti.core.service.MatchPickService;
import com.acorsetti.web.entity.FixturePredictionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SimpleController {

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private MatchPickService matchPickService;

    @Autowired
    private LeagueService leagueService;

    @RequestMapping(path= "/sample",method= RequestMethod.GET)
    public ModelAndView greet(){
        ModelAndView mav = new ModelAndView("sample");
        Map<String,String> colMaps = new HashMap<>();
        colMaps.put("dateCol","Date");
        colMaps.put("countryCol","Country");
        colMaps.put("leagueCol","League");
        colMaps.put("homeTeamCol","Home Team");
        colMaps.put("awayTeamCol","Away Team");
        colMaps.put("resultCol","Result");
        colMaps.put("hdaHomeCol","1");
        colMaps.put("hdaDrawCol","X");
        colMaps.put("hdaAwayCol","2");
        colMaps.put("U2_5ol","Under 2,5");
        colMaps.put("O2_5Col","Over 2,5");
        colMaps.put("GoalCol","Goal");
        colMaps.put("noGoalCol","No Goal");


        mav.addAllObjects(colMaps);

        List<Fixture> fixtures = this.fixtureService.fixturesInPeriodByDB(LocalDate.now(), LocalDate.now().plusDays(3));
        List<FixturePredictionView> fixturePredictionViews = new ArrayList<>();
        fixtures.forEach(fixture -> {
            League league = this.leagueService.byId(fixture.getLeagueId());
            if ( league == null ) return;
            String home = fixture.getHomeTeamName();
            String away = fixture.getAwayTeamName();
            String date = fixture.getEventDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            String result = fixture.getFinalScore();
            MatchPick hdaHomeMatchPick = this.matchPickService.byFixtureAndMarket(fixture.getFixtureId(), MarketValue.HDA_HOME);
            MatchPick hdaDrawMatchPick = this.matchPickService.byFixtureAndMarket(fixture.getFixtureId(), MarketValue.HDA_DRAW);
            MatchPick hdaAwayMatchPick = this.matchPickService.byFixtureAndMarket(fixture.getFixtureId(), MarketValue.HDA_AWAY);
            MatchPick u2_5MatchPick = this.matchPickService.byFixtureAndMarket(fixture.getFixtureId(), MarketValue.U2_5);
            MatchPick o2_5MatchPick = this.matchPickService.byFixtureAndMarket(fixture.getFixtureId(), MarketValue.O2_5);
            MatchPick bttsYesMatchPick = this.matchPickService.byFixtureAndMarket(fixture.getFixtureId(), MarketValue.BTTS_YES);
            MatchPick bttsNoMatchPick = this.matchPickService.byFixtureAndMarket(fixture.getFixtureId(), MarketValue.BTTS_NO);

            FixturePredictionView fixturePredictionView = new FixturePredictionView(
                    league.getCountryName(), league.getLeagueName(), date, result, home, away,
                    hdaHomeMatchPick == null ? null: hdaHomeMatchPick.getChance().getValue(),
                    hdaDrawMatchPick == null ? null: hdaDrawMatchPick.getChance().getValue(),
                    hdaAwayMatchPick == null ? null: hdaAwayMatchPick.getChance().getValue(),
                    u2_5MatchPick == null ? null: u2_5MatchPick.getChance().getValue(),
                    o2_5MatchPick == null ? null: o2_5MatchPick.getChance().getValue(),
                    bttsYesMatchPick == null ? null: bttsYesMatchPick.getChance().getValue(),
                    bttsNoMatchPick == null ? null: bttsNoMatchPick.getChance().getValue());

            if ( fixturePredictionView.isPredictionEmpty() ) return;
            fixturePredictionViews.add(fixturePredictionView);

        });

        mav.addObject("matches", fixturePredictionViews);

        return mav;
    }
}

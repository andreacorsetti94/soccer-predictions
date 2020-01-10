package com.acorsetti.core.newodds;

import com.acorsetti.core.updater.FixtureUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OddsAndPickManager {

    @Autowired
    private PickGenerator pickGenerator;

    @Autowired
    private ValuePickOutcomeDefiner valuePickOutcomeDefiner;

    @Autowired
    private FixtureUpdater fixtureUpdater;

    public void manageOddsAndPicks(){
        System.out.println("Updating fixtures....");
        this.fixtureUpdater.updateCurrentFixtures();
        System.out.println("Updating fixtures complete. Updating value picks outcome...");
        this.valuePickOutcomeDefiner.defineValuePickOutcome();
        System.out.println("Updating value picks outcome complete. Saving odds and creating picks...");
        this.pickGenerator.saveOddsAndCreatePicks();
        System.out.println("Saving odds and creating picks complete.");
    }
}

package com.acorsetti.core.newodds;

import com.acorsetti.core.model.jpa.Fixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OddsSaver {

    @Autowired
    private NewOddsManager newOddsManager;


    public List<OddEntity> saveOdds(List<Fixture> fixtures){
        List<OddEntity> globalOddList = new ArrayList<>();
        int count = 1;
        for(Fixture f: fixtures){
            System.out.println("Count: " + count + " / " + fixtures.size());
            String id = f.getFixtureId();

            //if now() is after start of the match continue
            if( f.getEventDate().isBefore(LocalDateTime.now()) ) continue;

            List<OddEntity> oddEntities = this.newOddsManager.manage(id);
            globalOddList.addAll(oddEntities);
            count++;
        }
        return globalOddList;
    }
}

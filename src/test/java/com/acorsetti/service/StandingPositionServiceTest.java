package com.acorsetti.service;


import com.acorsetti.SpringDataApplication;
import com.acorsetti.config.HibernateConfigTest;
import com.acorsetti.model.jpa.StandingPosition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class StandingPositionServiceTest {

    @Autowired
    private StandingPositionService standingPositionService;

    @Test
    public void testStandingPositionsByLeague(){
        String leagueId = "94";
        List<StandingPosition> positionList = this.standingPositionService.byLeague(leagueId);

        assertTrue(positionList.size() > 0);

        //check order of ranks
        StandingPosition tmpStandingPosition = positionList.get(0);
        int count = 1;
        while( count < positionList.size() ){
            StandingPosition currentStandingPosition = positionList.get(count);
            assertTrue(currentStandingPosition.getPosition() >= tmpStandingPosition.getPosition());
            tmpStandingPosition = currentStandingPosition;
            count++;
        }

        assertEquals(0, this.standingPositionService.byLeague(null).size());
    }
}

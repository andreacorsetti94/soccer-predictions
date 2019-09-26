package com.acorsetti.core.service;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class BetServiceTest {

    @Autowired
    private BetService betService;

    @Test
    public void testDBOpenBetsRetrieving(){
        assertTrue(this.betService.openBets().size() > 0);
    }

    @Test
    public void testAlgoPlacedBetOnFixture(){
        String algo1 = "Abbey";
        String fixture1 = "107310";

        String algo2 = "Abbey";
        String fixture2 = "107311";

        assertTrue( this.betService.hasAlgoAlreadyPlacedBetOnFixture(algo1, fixture1) );
        assertFalse( this.betService.hasAlgoAlreadyPlacedBetOnFixture(algo2, fixture2) );

    }

    @Test
    public void testBetsProfitUpdate(){
        //TODO
    }
}

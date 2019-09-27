package com.acorsetti.core.service.probabilities;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.eval.Chance;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigWebContextLoader.class)
@Transactional
@WebAppConfiguration
@ActiveProfiles("test")
public class MarketValuesPoissonMapperServiceTest {

    @Autowired
    private MarketValuesPoissonMapperService marketValuesPoissonMapperService;

    private static Map<MarketValue, Chance> exactScoreMap;

    @BeforeClass
    public static void setUpTests(){
        exactScoreMap = new HashMap<>();
        exactScoreMap.put(MarketValue.ONE_ONE,new Chance(0.2));
        exactScoreMap.put(MarketValue.OTHER,new Chance(0.05));
        exactScoreMap.put(MarketValue.NIL_TWO,new Chance(0.11));
        exactScoreMap.put(MarketValue.THREE_NIL,new Chance(0.18));
        exactScoreMap.put(MarketValue.FOUR_FOUR,new Chance(0.12));
        exactScoreMap.put(MarketValue.NIL_NIL,new Chance(0.25));

    }

    @Test
    public void testOverComputation(){
        Chance chance5 = this.marketValuesPoissonMapperService.computeOver(exactScoreMap,0);
        assertEquals(chance5, new Chance(0.66));

        Chance chance = this.marketValuesPoissonMapperService.computeOver(exactScoreMap,1);
        assertEquals(chance, new Chance(0.66));

        Chance chance2 = this.marketValuesPoissonMapperService.computeOver(exactScoreMap,2);
        assertEquals(chance2, new Chance(0.35));

        Chance chance3 = this.marketValuesPoissonMapperService.computeOver(exactScoreMap,3);
        assertEquals(chance3, new Chance(0.17));

        Chance chance4 = this.marketValuesPoissonMapperService.computeOver(exactScoreMap,4);
        assertEquals(chance4, new Chance(0.17));
    }


    @Test
    public void testHomeFTComputation(){
        Chance chance = this.marketValuesPoissonMapperService.computeHomeFT(exactScoreMap);
        assertEquals(chance, new Chance(0.18));
    }


    @Test
    public void testAwayFTComputation(){
        Chance chance = this.marketValuesPoissonMapperService.computeAwayFT(exactScoreMap);
        assertEquals(chance, new Chance(0.11));
    }


    @Test
    public void testGoalFTComputation(){
        Chance chance = this.marketValuesPoissonMapperService.computeGoalFT(exactScoreMap);
        assertEquals(chance, new Chance(0.32));
    }

    @Test
    public void testNotLegitInput(){
        Map<MarketValue, Chance> anotherMap = new HashMap<>();
        Chance chance1 = this.marketValuesPoissonMapperService.computeGoalFT(anotherMap);
        Chance chance2 = this.marketValuesPoissonMapperService.computeAwayFT(anotherMap);
        Chance chance3 = this.marketValuesPoissonMapperService.computeHomeFT(anotherMap);
        Chance chance4 = this.marketValuesPoissonMapperService.computeOver(anotherMap,3);

        assertTrue(chance1.equals(new Chance(0.0)) && chance2.equals(new Chance(0.0)) && chance3.equals(new Chance(0.0))
                && chance4.equals(new Chance(0.0)) );

    }
}

package com.acorsetti.core.service;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.eval.Chance;
import com.acorsetti.core.model.eval.PickValue;
import com.acorsetti.core.model.odds.OddsValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class PickValueCalculatorServiceTest {

    @Autowired
    private PickValueCalculatorService pickValueCalculatorService;

    @Test
    public void testPickValueCalculation(){
        Chance chance1 = new Chance(34); //34%
        OddsValue oddsValue1 = new OddsValue(1.34);
        PickValue pickValue1 = this.pickValueCalculatorService.calculatePickValue(chance1,oddsValue1);
        assertEquals(-0.41, pickValue1.getValue(), 0.0);

        Chance chance2 = new Chance(0.71); //71%
        OddsValue oddsValue2 = new OddsValue(1.45);
        PickValue pickValue2 = this.pickValueCalculatorService.calculatePickValue(chance2,oddsValue2);
        assertEquals(0.02, pickValue2.getValue(), 0.0);

        Chance chance3 = new Chance(1);
        OddsValue oddsValue3 = new OddsValue(1.67);
        PickValue pickValue3 = this.pickValueCalculatorService.calculatePickValue(chance3,oddsValue3);
        assertEquals(0.39,pickValue3.getValue(), 0.0);

        Chance chance4 = new Chance(0);
        OddsValue oddsValue4 = new OddsValue(0);
        PickValue pickValue4 = this.pickValueCalculatorService.calculatePickValue(chance4,oddsValue4);
        assertEquals(0,pickValue4.getValue(), 0.0);

        Chance chance5 = new Chance(1);
        OddsValue oddsValue5 = new OddsValue(1);
        PickValue pickValue5 = this.pickValueCalculatorService.calculatePickValue(chance5,oddsValue5);
        assertEquals(0,pickValue5.getValue(), 0.0);

        Chance chance6 = new Chance(0);
        OddsValue oddsValue6 = new OddsValue(1.67);
        PickValue pickValue6 = this.pickValueCalculatorService.calculatePickValue(chance6,oddsValue6);
        assertEquals(-0.59,pickValue6.getValue(), 0.0);
    }
}

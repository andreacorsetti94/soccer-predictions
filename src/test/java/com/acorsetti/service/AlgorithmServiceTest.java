package com.acorsetti.service;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.config.HibernateConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class AlgorithmServiceTest {

    @Autowired
    private AlgorithmService algorithmService;

    @Test
    public void testDBAlgoRetrieving(){
        assertTrue( this.algorithmService.listAllAlgorithms().size() > 0);
    }
}

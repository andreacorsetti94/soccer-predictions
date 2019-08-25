package com.acorsetti;

import com.acorsetti.config.HibernateConfigTest;
import com.acorsetti.repository.TeamRepository;
import com.acorsetti.service.TeamService;
import com.acorsetti.service.impl.TeamServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = { HibernateConfigTest.class },
		loader = AnnotationConfigContextLoader.class)
@Transactional
public class SpringDataApplicationTests {

	@Resource
	private TeamRepository teamRepository;

	@Test
	public void findTestTeams() {

		assert (this.teamRepository.findByTeamId("prova_team") != null);
	}

}

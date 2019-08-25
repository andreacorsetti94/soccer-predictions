package com.acorsetti;

import com.acorsetti.service.impl.FixtureServiceImpl;
import com.acorsetti.service.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	@Autowired
	private FixtureServiceImpl fixtureService;

	@Autowired
	private TeamServiceImpl teamService;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String args[]) {
		//System.out.println(this.fixtureService.fixturesByDay("2019"));
		System.out.println(this.teamService.listAllTeams().toString());
	}
}

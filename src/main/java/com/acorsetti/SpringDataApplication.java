package com.acorsetti;

import com.acorsetti.service.impl.FixtureServiceImpl;
import com.acorsetti.service.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;

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
		System.out.println(this.fixtureService.lastTeamMatches("4091", 10, "523","522").size());
	}
}

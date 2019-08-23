package com.acorsetti;

import com.acorsetti.service.impl.BetServiceImpl;
import com.acorsetti.service.impl.CountryServiceImpl;
import com.acorsetti.service.impl.FixtureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	@Autowired
	private FixtureServiceImpl fixtureService;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String args[]) {
		System.out.println(this.fixtureService.fixturesByDay("2019"));
	}
}

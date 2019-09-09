package com.acorsetti;

import com.acorsetti.api.APIFixtureRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Autowired
	private APIFixtureRetriever apiFixtureRetriever;

	@Override
	public void run(String args[]) {

		System.out.println(this.apiFixtureRetriever.byDay(LocalDate.of(2019, Month.AUGUST, 31)).getBody());
	}
}

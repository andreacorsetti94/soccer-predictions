package com.acorsetti;

import com.acorsetti.api.APIFixtureRetriever;
import com.acorsetti.updater.SeasonUpdater;
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

	@Override
	public void run(String args[]) {
	}
}

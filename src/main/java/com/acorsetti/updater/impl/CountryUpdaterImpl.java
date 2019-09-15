package com.acorsetti.updater.impl;


import com.acorsetti.api.APICountryRetriever;
import com.acorsetti.api.APIResponse;
import com.acorsetti.model.jpa.Country;
import com.acorsetti.repository.CountryRepository;
import com.acorsetti.updater.CountryUpdater;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Configuration
@Service
@PropertySource("classpath:scheduler.properties")
public class CountryUpdaterImpl implements CountryUpdater {
    private static final Logger logger = Logger.getLogger(CountryUpdaterImpl.class);

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private APICountryRetriever apiCountryRetriever;

    @Override
    @Scheduled(cron="${cron.countryUpdater}")
    public void updateCountries() {
        APIResponse<Country> apiResponse = this.apiCountryRetriever.allCountries();
        List<Country> countries = apiResponse.getBody();
        logger.info("Country Update. Countries retrieved: " + countries);
        this.countryRepository.saveAll(countries);
        logger.info("Retrieved countries saved.");
    }
}

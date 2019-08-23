package com.acorsetti.service.impl;

import com.acorsetti.model.Country;
import com.acorsetti.repository.CountryRepository;
import com.acorsetti.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> allCountries(){
        List<Country> countries = new ArrayList<>();
        this.countryRepository.findAll().forEach(countries::add);
        return countries;
    }
}

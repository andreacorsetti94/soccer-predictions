package com.acorsetti.core.service.impl;

import com.acorsetti.core.model.jpa.Country;
import com.acorsetti.core.repository.CountryRepository;
import com.acorsetti.core.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> allCountries(){
        return this.countryRepository.findAll();
    }

    public Country byCountryName(String name){
        List<Country> countries = this.countryRepository.findByCountryName(name);
        return countries.size() > 0 ? countries.get(0) : null;
    }
}

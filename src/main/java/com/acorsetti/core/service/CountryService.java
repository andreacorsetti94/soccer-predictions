package com.acorsetti.core.service;

import com.acorsetti.core.model.jpa.Country;

import java.util.List;

public interface CountryService {
    List<Country> allCountries();
    Country byCountryName(String name);
}

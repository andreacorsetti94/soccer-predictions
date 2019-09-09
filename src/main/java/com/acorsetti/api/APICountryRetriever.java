package com.acorsetti.api;

import com.acorsetti.model.jpa.Country;

public interface APICountryRetriever {
    APIResponse<Country> allCountries();
}

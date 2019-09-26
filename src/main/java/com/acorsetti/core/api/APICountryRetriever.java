package com.acorsetti.core.api;

import com.acorsetti.core.model.jpa.Country;

public interface APICountryRetriever {
    APIResponse<Country> allCountries();
}

package com.acorsetti.api;

import com.acorsetti.api.response.CountryResponse;

public interface APICountryRetriever extends APIRetriever {
    CountryResponse allCountriesByAPI();
}

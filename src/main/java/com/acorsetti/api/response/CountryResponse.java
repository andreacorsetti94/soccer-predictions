package com.acorsetti.api.response;

import com.acorsetti.model.jpa.Country;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CountryResponse extends APIResponse<Country> {

    private List<Country> countryList;

    public CountryResponse(HttpStatus response, int results, List<Country> countryList) {
        super(response, results);
        this.countryList = countryList;
    }

    @Override
    public List<Country> getBody() {
        return this.countryList;
    }

}

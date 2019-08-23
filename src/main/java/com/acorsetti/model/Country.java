package com.acorsetti.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {

    @Id
    private String countryId;
    private String countryName;

    public Country() {
    }

    public String getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public Country(String countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }


    @Override
    public String toString() {
        return "Country{" +
                "countryId='" + countryId + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }

}

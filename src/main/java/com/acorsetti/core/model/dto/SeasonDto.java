package com.acorsetti.core.model.dto;

public class SeasonDto {

    private String year;

    public SeasonDto() {
    }

    public SeasonDto(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

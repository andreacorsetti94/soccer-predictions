package com.acorsetti.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Season {

    @Id
    private String year;

    public Season(){}
    public Season(String year) {
        this.year = year;
    }

    public String getYear(){
        return year;
    }

    @Override
    public String toString() {
        return "Season{" +
                "year='" + year + '\'' +
                '}';
    }
}
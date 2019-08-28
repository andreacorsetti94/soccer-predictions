package com.acorsetti.model.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Algorithm {

    @Id
    private String id;

    public Algorithm() {
    }

    public Algorithm(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Algorithm{" +
                "id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }
}

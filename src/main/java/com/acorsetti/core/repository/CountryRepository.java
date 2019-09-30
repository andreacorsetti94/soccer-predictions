package com.acorsetti.core.repository;

import com.acorsetti.core.model.jpa.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CountryRepository extends PagingAndSortingRepository<Country,String> {

    List<Country> findAll();
    List<Country> findByCountryName(String name);
}

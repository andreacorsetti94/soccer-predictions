package com.acorsetti.repository;

import com.acorsetti.model.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryRepository extends PagingAndSortingRepository<Country,String> {

    Iterable<Country> findAll();
}

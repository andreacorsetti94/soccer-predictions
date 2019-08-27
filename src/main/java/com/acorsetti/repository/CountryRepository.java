package com.acorsetti.repository;

import com.acorsetti.model.jpa.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CountryRepository extends PagingAndSortingRepository<Country,String> {

    List<Country> findAll();

}

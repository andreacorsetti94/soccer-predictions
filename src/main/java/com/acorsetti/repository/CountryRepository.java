package com.acorsetti.repository;

import com.acorsetti.model.Country;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CountryRepository extends PagingAndSortingRepository<Country,String> {

    List<Country> findAll();

}

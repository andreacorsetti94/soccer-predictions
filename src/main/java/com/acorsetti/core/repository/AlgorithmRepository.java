package com.acorsetti.core.repository;

import com.acorsetti.core.model.jpa.Algorithm;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlgorithmRepository extends PagingAndSortingRepository<Algorithm,String> {
    Iterable<Algorithm> findAll();
}

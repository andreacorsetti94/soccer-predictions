package com.acorsetti.repository;

import com.acorsetti.model.Algorithm;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlgorithmRepository extends PagingAndSortingRepository<Algorithm,String> {
    Iterable<Algorithm> findAll();
}

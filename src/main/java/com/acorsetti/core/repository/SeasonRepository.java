package com.acorsetti.core.repository;

import com.acorsetti.core.model.jpa.Season;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SeasonRepository extends PagingAndSortingRepository<Season,String> {

    List<Season> findAll();
    Season findByYear(String year);

}

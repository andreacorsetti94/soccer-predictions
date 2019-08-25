package com.acorsetti.repository;

import com.acorsetti.model.Fixture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FixtureRepository extends PagingAndSortingRepository<Fixture,String> {
    @Query("FROM Fixture WHERE EVENTDATE LIKE ?1")
    List<Fixture> findByEventDateOrderByDateAsc(String day);

}

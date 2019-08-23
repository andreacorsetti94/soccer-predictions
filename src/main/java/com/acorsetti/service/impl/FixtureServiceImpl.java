package com.acorsetti.service.impl;

import com.acorsetti.model.Fixture;
import com.acorsetti.repository.FixtureRepository;
import com.acorsetti.service.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixtureServiceImpl implements FixtureService {

    @Autowired
    private FixtureRepository fixtureRepository;

    public List<Fixture> fixturesByDay(String dayLike){
        return this.fixtureRepository.findByEventDateOrderByDateAsc(dayLike);
    }
}

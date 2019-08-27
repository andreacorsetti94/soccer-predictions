package com.acorsetti.service.impl;

import com.acorsetti.model.jpa.Season;
import com.acorsetti.repository.SeasonRepository;
import com.acorsetti.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    @Override
    public List<Season> allSeasons() {
        return this.seasonRepository.findAll();
    }

    @Override
    public Season byYear(String year) {
        return this.seasonRepository.findByYear(year);
    }
}

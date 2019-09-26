package com.acorsetti.core.service.impl;

import com.acorsetti.core.model.jpa.Algorithm;
import com.acorsetti.core.repository.AlgorithmRepository;
import com.acorsetti.core.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {

    @Autowired
    private AlgorithmRepository algorithmRepository;

    public List<Algorithm> listAllAlgorithms(){
        List<Algorithm> algorithms = new ArrayList<>();
        this.algorithmRepository.findAll().forEach(algorithms::add);
        return algorithms;
    }
}

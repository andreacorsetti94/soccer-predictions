package com.acorsetti.service.impl;

import com.acorsetti.model.jpa.Algorithm;
import com.acorsetti.repository.AlgorithmRepository;
import com.acorsetti.service.AlgorithmService;
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

package com.example.test.strategy.service;

import com.example.test.strategy.model.controller.MemberEnrollRequest;
import com.example.test.strategy.model.Rules;
import com.example.test.strategy.repository.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private RulesRepository rulesRepository;

    public String enroll(MemberEnrollRequest memberEnrollRequest) {
        //Perform

        //Perform Mongo Step
        List<Rules> rulesList = rulesRepository.findAll();

        return null;
    }
}

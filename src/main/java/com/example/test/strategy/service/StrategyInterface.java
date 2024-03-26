package com.example.test.strategy.service;

import com.example.test.strategy.model.Rules;
import com.example.test.strategy.model.controller.MemberRegisterationRequest;
import org.springframework.stereotype.Component;

import java.util.List;

public interface StrategyInterface {
    public void enrollToAvion(List<Rules> rulesList, MemberRegisterationRequest memberRegisterationRequest);

    public void enrollWithPartner(List<Rules> rulesList, MemberRegisterationRequest memberRegisterationRequest);
}

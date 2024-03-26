package com.example.test.strategy.service.impl;

import com.example.test.strategy.constants.Constants;
import com.example.test.strategy.model.Rules;
import com.example.test.strategy.model.controller.MemberRegisterationRequest;
import com.example.test.strategy.service.EnrollmentService;
import com.example.test.strategy.service.StrategyInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class StrategyInterfaceImpl implements StrategyInterface {

    Logger logger = LoggerFactory.getLogger(EnrollmentService.class);

    public void enrollWithPartner(List<Rules> rulesList, MemberRegisterationRequest memberRegisterationRequest) {
        if(shouldExecuteRule(rulesList, memberRegisterationRequest.getPartnerCode(), Constants.RULES_KEY_ENROLL_VIA_PARTNER)) {
            //call YC10 endpoint via WY70/loyalty-adapter
            logger.info("Enrolled customer on Partner Downstream Systems");
        } else {
            logger.info("Partner Downstream System could not be invoked as the rule does not apply");
        }
    }

    public void enrollToAvion(List<Rules> rulesList, MemberRegisterationRequest memberRegisterationRequest) {

        if(shouldExecuteRule(rulesList, memberRegisterationRequest.getPartnerCode(), Constants.KEY_AVION_ENROLL)) {
            //call YC10 endpoints via WY70/loyalty-adapter
            logger.info("YC10 was invoked to enroll member to Avion Rewards");
        } else {
            logger.info("YC10 could not be invoked as the rule does not apply");
        }
    }

    private boolean shouldExecuteRule(List<Rules> rulesList, String partnerCode, String ruleKey) {
        Boolean shouldExecuteRule = false;
        Rules rule = rulesList.get(0);
        Map<String, Map<String, Boolean>> ruleEnroll = rule.getEnroll();
        Map<String, Boolean> partnerRuleMap = null;

        switch (ruleKey) {
            case Constants.KEY_AVION_ENROLL:
                partnerRuleMap = ruleEnroll.get(Constants.KEY_AVION_ENROLL);
                shouldExecuteRule = partnerRuleMap.getOrDefault(partnerCode, false);
                break;

            case Constants.RULES_KEY_ENROLL_VIA_PARTNER:
                partnerRuleMap = ruleEnroll.get(Constants.RULES_KEY_ENROLL_VIA_PARTNER);
                shouldExecuteRule = partnerRuleMap.getOrDefault(partnerCode, false);
                break;
        }

        return shouldExecuteRule;
    }
}

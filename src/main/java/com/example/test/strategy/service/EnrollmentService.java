package com.example.test.strategy.service;

import com.example.test.strategy.constants.Constants;
import com.example.test.strategy.model.AccountPartnerMap;
import com.example.test.strategy.model.Rules;
import com.example.test.strategy.model.controller.MemberRegisterationRequest;
import com.example.test.strategy.model.PartnerMaster;
import com.example.test.strategy.model.PartnerMember;
import com.example.test.strategy.repository.AccountPartnerMapRepository;
import com.example.test.strategy.repository.RulesRepository;
import com.example.test.strategy.repository.PartnerMasterRepository;
import com.example.test.strategy.repository.PartnerMemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class EnrollmentService {

    Logger logger = LoggerFactory.getLogger(EnrollmentService.class);

    @Autowired
    private PartnerMasterRepository partnerMasterRepository;

    @Autowired
    private PartnerMemberRepository partnerMemberRepository;

    @Autowired
    private AccountPartnerMapRepository accountPartnerMapRepository;

    @Autowired
    private RulesRepository rulesRepository;

    @Autowired
    private StrategyInterface strategyInterface;

    @Transactional
    public void enrollMember(String partnerLoyaltyId, String partnerCode, String clientCard) {
        // Task 1: Fetch ID from PTNR_MSTR table for partnerCode = 'GOOGLE'
    }

    @Transactional
    public void registerMember(MemberRegisterationRequest memberRegisterationRequest) {
        //Implement the core feature
        registerMemberToLLDB(memberRegisterationRequest);

        //Define all possible steps which may occur for one or more partner
        List<Rules> rulesList = rulesRepository.findAll(); //The object "rulesList" can be stored in in-Memory Spring Cache
        //enrollToAvion(rulesList, memberRegisterationRequest);
        //enrollWithPartner(rulesList, memberRegisterationRequest);

        strategyInterface.enrollToAvion(rulesList, memberRegisterationRequest);
        strategyInterface.enrollWithPartner(rulesList, memberRegisterationRequest);
    }

    private void registerMemberToLLDB(MemberRegisterationRequest memberRegisterationRequest) {
        String partnerCode = memberRegisterationRequest.getPartnerCode();
        String partnerLoyaltyId = memberRegisterationRequest.getPartnerLoyaltyId();

        PartnerMaster partnerMaster = partnerMasterRepository.findByPartnerCode(partnerCode);

        if (partnerMaster != null) {
            PartnerMember partnerMember = new PartnerMember();
            partnerMember.setId(Double.valueOf(Math.random()).intValue());
            partnerMember.setPartnerLoyaltyId(partnerLoyaltyId);
            partnerMember.setPartnerMaster(partnerMaster);

            try {
                //Enroll the member by persiting the info locally
                partnerMemberRepository.save(partnerMember);


                /*AccountPartnerMap accountPartnerMap = new AccountPartnerMap();
                accountPartnerMap.setClientAccount(null);
                accountPartnerMap.setPartnerMember(partnerMember);

                accountPartnerMapRepository.save(accountPartnerMap);*/
            } catch (Exception e) {
                throw e;
            } finally {
                logger.info("Registration record was saved to LL DB");
            }
        } else {
            // Handle if partnerCode is not found
            throw new RuntimeException("Partner with code - " + partnerCode + " not found");
        }
    }

    private List<AccountPartnerMap> addPartnerMemberToList(AccountPartnerMap accountPartnerMap) {
        List<AccountPartnerMap> partnerMemberList = new ArrayList<>();
        partnerMemberList.add(accountPartnerMap);

        return partnerMemberList;
    }

    @Transactional
    public void unenrollMember(MemberRegisterationRequest memberRegisterationRequest) {
        // Retrieve PartnerMaster entity based on partnerCode
        PartnerMaster partnerMaster = partnerMasterRepository.findByPartnerCode(memberRegisterationRequest.getPartnerCode());
        if (partnerMaster != null) {
            // Retrieve PartnerMember entity based on partnerLoyaltyId and PTNR_MSTR_ID
            PartnerMember partnerMember = partnerMemberRepository.findByPartnerLoyaltyIdAndPartnerMaster(memberRegisterationRequest.getPartnerLoyaltyId(), partnerMaster);
            if (partnerMember != null) {
                // Remove the retrieved PartnerMember entity
                partnerMemberRepository.delete(partnerMember);
            } else {
                // Handle the case where no matching PartnerMember is found
                throw new RuntimeException("Partner member not found.");
            }
        } else {
            // Handle the case where no matching PartnerMaster is found
            throw new RuntimeException("Partner master record not found.");
        }
    }
}



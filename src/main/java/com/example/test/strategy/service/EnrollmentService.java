package com.example.test.strategy.service;

import com.example.test.strategy.model.AccountPartnerMap;
import com.example.test.strategy.model.Rules;
import com.example.test.strategy.model.controller.MemberEnrollRequest;
import com.example.test.strategy.model.PartnerMaster;
import com.example.test.strategy.model.PartnerMember;
import com.example.test.strategy.repository.AccountPartnerMapRepository;
import com.example.test.strategy.repository.RulesRepository;
import com.example.test.strategy.repository.PartnerMasterRepository;
import com.example.test.strategy.repository.PartnerMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private PartnerMasterRepository partnerMasterRepository;

    @Autowired
    private PartnerMemberRepository partnerMemberRepository;

    @Autowired
    private AccountPartnerMapRepository accountPartnerMapRepository;

    @Autowired
    private RulesRepository rulesRepository;

    @Transactional
    public void enrollMember(String partnerLoyaltyId, String partnerCode, String clientCard) {
        // Task 1: Fetch ID from PTNR_MSTR table for partnerCode = 'GOOGLE'

    }

    @Transactional
    public void enrollMember(MemberEnrollRequest memberEnrollRequest) {
        String partnerCode = memberEnrollRequest.getPartnerCode();
        String partnerLoyaltyId = memberEnrollRequest.getPartnerLoyaltyId();

        PartnerMaster partnerMaster = partnerMasterRepository.findByPartnerCode(partnerCode);

        if (partnerMaster != null) {
            PartnerMember partnerMember = new PartnerMember();
            partnerMember.setId(Double.valueOf(Math.random()).intValue());
            partnerMember.setPartnerLoyaltyId(partnerLoyaltyId);
            partnerMember.setPartnerMaster(partnerMaster);

            try {
                //Enroll the member by persiting the info locally
                partnerMemberRepository.save(partnerMember);

                //Perform Avion enrollment
                List<Rules> rulesList = rulesRepository.findAll();

                //Perform partner Enrollment (External)

                //Save an entry into the ACCNT_PTNR_MAP table
                /*AccountPartnerMap accountPartnerMap = new AccountPartnerMap();
                accountPartnerMap.setClientAccount(null);
                accountPartnerMap.setPartnerMember(partnerMember);

                accountPartnerMapRepository.save(accountPartnerMap);*/
            } catch (Exception e) {
                throw e;
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
    public void unenrollMember(MemberEnrollRequest memberEnrollRequest) {
        // Retrieve PartnerMaster entity based on partnerCode
        PartnerMaster partnerMaster = partnerMasterRepository.findByPartnerCode(memberEnrollRequest.getPartnerCode());
        if (partnerMaster != null) {
            // Retrieve PartnerMember entity based on partnerLoyaltyId and PTNR_MSTR_ID
            PartnerMember partnerMember = partnerMemberRepository.findByPartnerLoyaltyIdAndPartnerMaster(memberEnrollRequest.getPartnerLoyaltyId(), partnerMaster);
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



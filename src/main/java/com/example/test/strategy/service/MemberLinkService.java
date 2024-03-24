package com.example.test.strategy.service;

import com.example.test.strategy.model.*;
import com.example.test.strategy.model.controller.MemberLinkRequest;
import com.example.test.strategy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberLinkService {

    @Autowired
    private ClientProfileRepository clientProfileRepository;

    @Autowired
    private ClientAccountRepository clientAccountRepository;

    @Autowired
    private AccountPartnerMapRepository accountPartnerMapRepository;

    @Autowired
    private PartnerMemberRepository partnerMemberRepository;

    @Autowired
    private PartnerMasterRepository partnerMasterRepository;

    @Transactional
    public void link(MemberLinkRequest linkRequest) throws Exception {
        // Insert data into CLNT_PRFL table
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setClientCardNumber(linkRequest.getClientCard());
        // Set other properties if needed
        clientProfileRepository.save(clientProfile);

        // Insert data into CLNT_ACCNT table, avoiding duplicates
        for (ClientAccount clientAccount : linkRequest.getClientAccountList()) {
            ClientAccount existingClientAccount = clientAccountRepository.findByPaymentCardNumber(clientAccount.getPaymentCardNumber());
            if (existingClientAccount == null) {
                existingClientAccount = clientAccount;
                clientAccount.setClientProfile(clientProfile);
                clientAccountRepository.save(existingClientAccount);
            }

            PartnerMember partnerMember = getPartnerMember(linkRequest.getPartnerCode(), linkRequest.getPartnerLoyaltyId());
            AccountPartnerMap accountPartnerMap = null;

            if(partnerMember != null) {
                accountPartnerMap = accountPartnerMapRepository.findByPartnerMemberAndClientAccount(partnerMember, existingClientAccount);
            }
            if(accountPartnerMap == null) {
                AccountPartnerMap newAccountPartnerMap = new AccountPartnerMap();
                newAccountPartnerMap.setClientAccount(existingClientAccount);
                newAccountPartnerMap.setPartnerMember(partnerMember);
                accountPartnerMapRepository.save(newAccountPartnerMap);
            } else {
                throw new Exception("Link already exists");
            }
        }
    }

    // Method to retrieve PartnerMember (Replace with your implementation)
    private PartnerMember getPartnerMember(String partnerCode, String partnerLoyaltyId) {
        PartnerMember partnerMember = null;
        PartnerMaster partnerMaster = partnerMasterRepository.findByPartnerCode(partnerCode);
        if (partnerMaster != null) {
            partnerMember = partnerMemberRepository.findByPartnerLoyaltyIdAndPartnerMaster(partnerLoyaltyId, partnerMaster);
            if (partnerMember == null) {
                throw new RuntimeException("Partner member not found.");
            }
        } else {
            throw new RuntimeException("Partner code not found.");
        }

        return partnerMember;
    }
}


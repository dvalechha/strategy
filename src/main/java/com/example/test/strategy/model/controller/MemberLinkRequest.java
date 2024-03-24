package com.example.test.strategy.model.controller;

import com.example.test.strategy.model.ClientAccount;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberLinkRequest {

    @JsonProperty("paymentCard")
    List<ClientAccount> clientAccountList;

    String clientCard;

    String partnerCode;

    String partnerLoyaltyId;
}

package com.example.test.strategy.model.controller;

import com.example.test.strategy.constants.ClientIdType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberEnrollRequest {

    private String partnerLoyaltyId;
    private String clientCard;
    private ClientIdType clientIdType;
    private String partnerCode;

}

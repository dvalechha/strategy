package com.example.test.strategy.model.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUnLinkRequest {

    String clientCard;

    String partnerCode;

    String partnerLoyaltyId;
}

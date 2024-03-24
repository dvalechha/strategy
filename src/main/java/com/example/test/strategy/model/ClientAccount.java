package com.example.test.strategy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CLNT_ACCNT")
@Getter
@Setter
public class ClientAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLNT_ACCNT_ID")
    private Long id;

    @Column(name = "PAYMENT_CRD_NUM")
    private String paymentCardNumber;

    @Column(name = "LID_HASH")
    private String lidHash;

    @Column(name = "CLNT_ACCNT_TYPE")
    private String clientAccountType;

    @ManyToOne
    @JoinColumn(name = "CLNT_PRFL_ID", nullable = false)
    private ClientProfile clientProfile;

//    @OneToOne
//    @JoinColumn(name = "PRD_MASTR_ID", referencedColumnName = "PRD_MASTR_ID")
//    private ProductMaster productMaster;

    @OneToOne
    @JoinColumn(name = "PRD_MASTR_ID", referencedColumnName = "PRD_MASTR_ID")
    private PartnerProductMap partnerProductMap;

    @OneToMany(mappedBy = "clientAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountPartnerMap> accountPartnerMaps;

    @OneToMany(mappedBy = "clientAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountToken> accountTokens;

    // Getters and setters
}



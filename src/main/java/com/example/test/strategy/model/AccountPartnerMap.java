package com.example.test.strategy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ACCNT_PTNR_MAP",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CLNT_ACCNT_ID", "PTNR_MEMBR_ID"})})
@Getter
@Setter
public class AccountPartnerMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCNT_PTNR_MAP_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CLNT_ACCNT_ID")
    private ClientAccount clientAccount;

    @ManyToOne
    @JoinColumn(name = "PTNR_MEMBR_ID")
    private PartnerMember partnerMember;
}


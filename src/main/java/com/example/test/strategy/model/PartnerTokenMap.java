package com.example.test.strategy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PTNR_TKN_MAP")
public class PartnerTokenMap {
    @Id
    @Column(name = "PTNR_TKN_MAP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partnerTokenMapId;

    @OneToOne
    @JoinColumn(name = "ACCNT_TKN_ID", referencedColumnName = "ACCNT_TKN_ID")
    private AccountToken accountToken;

    @ManyToOne
    @JoinColumn(name = "PTNR_MSTR_ID", referencedColumnName = "PTNR_MSTR_ID")
    private PartnerMaster partnerMaster;

    // Getters and setters
}

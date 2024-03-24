package com.example.test.strategy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "PTNR_MEMBR",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"PTNR_LYLTY_ID", "PTNR_MSTR_ID"}))
@Getter
@Setter
public class PartnerMember {

    @Id
    @Column(name = "PTNR_MEMBR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PTNR_LYLTY_ID")
    private String partnerLoyaltyId;

    @ManyToOne
    @JoinColumn(name = "PTNR_MSTR_ID", nullable = false)
    private PartnerMaster partnerMaster;

    @OneToMany(mappedBy = "partnerMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountPartnerMap> accountPartnerMaps;

    // Getters and setters
}


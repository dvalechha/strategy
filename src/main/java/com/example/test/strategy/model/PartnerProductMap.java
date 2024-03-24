package com.example.test.strategy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PTNR_PRD_MAP", uniqueConstraints = {@UniqueConstraint(columnNames={"PTNR_MSTR_ID", "PRD_MASTR_ID"})})
@Getter
@Setter
public class PartnerProductMap {
    @Id
    @Column(name = "PTNR_PRD_MAP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partnerProductMapId;

    @ManyToOne
    @JoinColumn(name = "PTNR_MSTR_ID", referencedColumnName = "PTNR_MSTR_ID")
    private PartnerMaster partnerMaster;

    @ManyToOne
    @JoinColumn(name = "PRD_MASTR_ID", referencedColumnName = "PRD_MASTR_ID")
    private ProductMaster productMaster;

    // Getters and setters
}

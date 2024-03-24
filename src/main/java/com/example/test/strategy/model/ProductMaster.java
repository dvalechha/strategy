package com.example.test.strategy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "PRD_MASTR")
@Getter
@Setter

public class ProductMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRD_MASTR_ID")
    private Long id;

    @Column(name = "PRD_CD")
    private String productCode;

    @OneToMany(mappedBy = "productMaster")
    private List<PartnerProductMap> partnerProductMaps;
}

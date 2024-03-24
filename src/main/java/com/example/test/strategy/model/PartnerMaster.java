package com.example.test.strategy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "PTNR_MSTR",
        uniqueConstraints= @UniqueConstraint(columnNames={"PTNR_CODE", "PTNR_NAME"}))
@Getter
@Setter
public class PartnerMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PTNR_MSTR_ID")
    private Long id;

    @Column(name = "PTNR_CODE")
    private String partnerCode;

    @Column(name = "PTNR_NAME")
    private String partnerName;

    @OneToMany(mappedBy = "partnerMaster")
    private List<PartnerProductMap> partnerProductMaps;

    @OneToMany(mappedBy = "partnerMaster")
    private List<PartnerTokenMap> partnerTokenMaps;

    @OneToMany(mappedBy = "partnerMaster")
    private List<PartnerMember> partnerMembers;
    // Getters and setters
}

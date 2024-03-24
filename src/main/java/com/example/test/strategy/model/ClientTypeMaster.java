package com.example.test.strategy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CLNT_TYP_MASTR")
@Getter
@Setter
public class ClientTypeMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLNT_TYP_MASTR_ID")
    private Long id;

    @Column(name = "CLNT_TYP_CD")
    private String clientTypeCode;

    @OneToOne(mappedBy = "clientTypeMaster")
    private ClientProfile clientProfile;
}

package com.example.test.strategy.service;

import com.example.test.strategy.model.PartnerMaster;
import com.example.test.strategy.model.PartnerProductMap;
import com.example.test.strategy.model.ProductMaster;
import com.example.test.strategy.repository.PartnerMasterRepository;
import com.example.test.strategy.repository.PartnerProductMapRepository;
import com.example.test.strategy.repository.ProductMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PartnerMasterRepository partnerMasterRepository;

    @Autowired
    private ProductMasterRepository productMasterRepository;

    @Autowired
    private PartnerProductMapRepository partnerProductMapRepository;

    public void loadProductMaster(List<String> products) {
        List<ProductMaster> productMasterList = new ArrayList<>();
        products.forEach(product -> {
            ProductMaster productMaster = new ProductMaster();
            productMaster.setProductCode(product);
            productMasterList.add(productMaster);
        });
        productMasterRepository.saveAll(productMasterList);
    }

    public void loadPartnerMaster(List<String> partners) {
        List<PartnerMaster> partnerMasterList = new ArrayList<>();

        partners.forEach(partner -> {
            PartnerMaster partnerMaster = new PartnerMaster();
            partnerMaster.setPartnerCode(partner);
            partnerMaster.setPartnerName(partner);
            partnerMasterList.add(partnerMaster);
        });

        partnerMasterRepository.saveAll(partnerMasterList);
    }

    @Transactional
    public void loadProdPartnerMap() {
        List<PartnerMaster> partnerMasters = partnerMasterRepository.findAll();
        List<ProductMaster> productMasters = productMasterRepository.findAll();

        List<PartnerProductMap> partnerProductMapList = new ArrayList<>();

        for (PartnerMaster partnerMaster : partnerMasters) {
            for (ProductMaster productMaster : productMasters) {
                PartnerProductMap partnerProductMap = new PartnerProductMap();
                partnerProductMap.setPartnerMaster(partnerMaster);
                partnerProductMap.setProductMaster(productMaster);

                partnerProductMapList.add(partnerProductMap);
            }
        }

        partnerProductMapRepository.saveAll(partnerProductMapList);
    }

    public void truncateMasterTables() {
        partnerProductMapRepository.deleteAll();
        partnerMasterRepository.deleteAll();
        productMasterRepository.deleteAll();
    }
}

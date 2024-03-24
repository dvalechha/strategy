package com.example.test.strategy.controller;

import com.example.test.strategy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/load/products")
    public ResponseEntity<String> loadProductMaster(@RequestBody List<String> products) {
        adminService.loadProductMaster(products);
        return ResponseEntity.ok("Product Master loaded successfully");
    }

    @PostMapping("/load/partners")
    public ResponseEntity<String> loadPartnerMaster(@RequestBody List<String> partners) {
        adminService.loadPartnerMaster(partners);
        return ResponseEntity.ok("Partner Master loaded successfully");
    }

    @PostMapping("/create/product-partner-map")
    public ResponseEntity<String> loadProdPartnerMap() {
        adminService.loadProdPartnerMap();
        return ResponseEntity.ok("Product Partner Map loaded successfully");
    }

    @DeleteMapping("/truncate/master-tables")
    public ResponseEntity<String> clearTables() {
        adminService.truncateMasterTables();
        return ResponseEntity.ok("Tables cleared successfully");
    }
}

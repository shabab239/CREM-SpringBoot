// src/main/java/com/shabab/RealEstateManagementSystem/core/controller/RawMaterialController.java

package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.RawMaterial;
import com.shabab.RealEstateManagementSystem.core.model.RawMaterialOrder;
import com.shabab.RealEstateManagementSystem.core.model.RawMaterialStock;
import com.shabab.RealEstateManagementSystem.core.service.RawMaterialService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 30/09/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/rawMaterial")
public class RawMaterialController {

    @Autowired
    private RawMaterialService rawMaterialService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return rawMaterialService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody RawMaterial rawMaterial) {
        return rawMaterialService.save(rawMaterial);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody RawMaterial rawMaterial) {
        return rawMaterialService.update(rawMaterial);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return rawMaterialService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return rawMaterialService.deleteById(id);
    }

    @GetMapping("/orders")
    public ApiResponse getAllOrders() {
        return rawMaterialService.getAllOrders();
    }

    @PostMapping("/orders/save")
    public ApiResponse saveOrder(@Valid @RequestBody RawMaterialOrder rawMaterialOrder) {
        return rawMaterialService.saveOrder(rawMaterialOrder);
    }

    @PutMapping("/orders/update")
    public ApiResponse updateOrder(@Valid @RequestBody RawMaterialOrder rawMaterialOrder) {
        return rawMaterialService.updateOrder(rawMaterialOrder);
    }

    @GetMapping("/orders/{id}")
    public ApiResponse getOrderById(@PathVariable Long id) {
        return rawMaterialService.getOrderById(id);
    }

    @DeleteMapping("/orders/{id}")
    public ApiResponse deleteOrderById(@PathVariable Long id) {
        return rawMaterialService.deleteOrderById(id);
    }

    @GetMapping("/stocks")
    public ApiResponse getAllStocks() {
        return rawMaterialService.getAllStocks();
    }

    @PostMapping("/stocks/save")
    public ApiResponse saveStock(@Valid @RequestBody RawMaterialStock rawMaterialStock) {
        return rawMaterialService.saveStock(rawMaterialStock);
    }

    @PutMapping("/stocks/update")
    public ApiResponse updateStock(@Valid @RequestBody RawMaterialStock rawMaterialStock) {
        return rawMaterialService.updateStock(rawMaterialStock);
    }

    @GetMapping("/stocks/{id}")
    public ApiResponse getStockById(@PathVariable Long id) {
        return rawMaterialService.getStockById(id);
    }

    @DeleteMapping("/stocks/{id}")
    public ApiResponse deleteStockById(@PathVariable Long id) {
        return rawMaterialService.deleteStockById(id);
    }


}
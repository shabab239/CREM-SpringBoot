package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.RawMaterial;
import com.shabab.RealEstateManagementSystem.core.model.RawMaterialOrder;
import com.shabab.RealEstateManagementSystem.core.model.RawMaterialStock;
import com.shabab.RealEstateManagementSystem.core.model.Supplier;
import com.shabab.RealEstateManagementSystem.core.service.RawMaterialService;
import com.shabab.RealEstateManagementSystem.core.service.RawMaterialOrderService;
import com.shabab.RealEstateManagementSystem.core.service.RawMaterialStockService;
import com.shabab.RealEstateManagementSystem.core.service.SupplierService;
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
public class RawMaterialRestController {

    @Autowired
    private RawMaterialService rawMaterialService;

    @Autowired
    private RawMaterialOrderService rawMaterialOrderService;

    @Autowired
    private RawMaterialStockService rawMaterialStockService;

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/")
    public ApiResponse findAll() {
        return rawMaterialService.findAll();
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
        return rawMaterialService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return rawMaterialService.deleteById(id);
    }

    @GetMapping("/orders")
    public ApiResponse findAllOrders() {
        return rawMaterialOrderService.findAll();
    }

    @PostMapping("/orders/save")
    public ApiResponse saveOrder(@Valid @RequestBody RawMaterialOrder rawMaterialOrder) {
        return rawMaterialOrderService.save(rawMaterialOrder);
    }

    @PutMapping("/orders/update")
    public ApiResponse updateOrder(@Valid @RequestBody RawMaterialOrder rawMaterialOrder) {
        return rawMaterialOrderService.update(rawMaterialOrder);
    }

    @GetMapping("/orders/{id}")
    public ApiResponse getOrderById(@PathVariable Long id) {
        return rawMaterialOrderService.findById(id);
    }

    @DeleteMapping("/orders/{id}")
    public ApiResponse deleteOrderById(@PathVariable Long id) {
        return rawMaterialOrderService.deleteById(id);
    }

    @GetMapping("/stocks")
    public ApiResponse findAllStocks() {
        return rawMaterialStockService.findAll();
    }

    @PostMapping("/stocks/save")
    public ApiResponse saveStock(@Valid @RequestBody RawMaterialStock rawMaterialStock) {
        return rawMaterialStockService.save(rawMaterialStock);
    }

    @PutMapping("/stocks/update")
    public ApiResponse updateStock(@Valid @RequestBody RawMaterialStock rawMaterialStock) {
        return rawMaterialStockService.update(rawMaterialStock);
    }

    @GetMapping("/stocks/{id}")
    public ApiResponse getStockById(@PathVariable Long id) {
        return rawMaterialStockService.findById(id);
    }

    @DeleteMapping("/stocks/{id}")
    public ApiResponse deleteStockById(@PathVariable Long id) {
        return rawMaterialStockService.deleteById(id);
    }

    @GetMapping("/suppliers")
    public ApiResponse findAllSuppliers() {
        return supplierService.findAll();
    }

    @PostMapping("/suppliers/save")
    public ApiResponse saveSupplier(@Valid @RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    @PutMapping("/suppliers/update")
    public ApiResponse updateSupplier(@Valid @RequestBody Supplier supplier) {
        return supplierService.update(supplier);
    }

    @GetMapping("/suppliers/{id}")
    public ApiResponse getSupplierById(@PathVariable Long id) {
        return supplierService.findById(id);
    }

    @DeleteMapping("/suppliers/{id}")
    public ApiResponse deleteSupplierById(@PathVariable Long id) {
        return supplierService.deleteById(id);
    }

}
package com.alejandr.libreriajl.libreriajl_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alejandr.libreriajl.libreriajl_backend.dto.ProductDto;
import com.alejandr.libreriajl.libreriajl_backend.entity.Product;
import com.alejandr.libreriajl.libreriajl_backend.repository.MyProductRepo;
import com.alejandr.libreriajl.libreriajl_backend.specification.ProductSpecifications;

@Service
public class MyEntityService {
@Autowired
private MyProductRepo myProductRepo;

public Page<ProductDto>searchProduct(String name, String description,String brand, Pageable pageable){
    var specification= ProductSpecifications.filterProduct(name, brand, description);
    Page<Product>products= myProductRepo.findAll(specification, pageable);
    return products.map(product->new ProductDto(product.getId(),product.getBrand(),product.getName(),product.getPrice()));
}}
package com.alejandr.libreriajl.libreriajl_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandr.libreriajl.libreriajl_backend.dto.ProductDto;
import com.alejandr.libreriajl.libreriajl_backend.entity.Product;
import com.alejandr.libreriajl.libreriajl_backend.service.MyEntityService;

@RestController
public class ProductController{
    @Autowired
    private MyEntityService myEntityService;

    @GetMapping("/products")
    public Page<ProductDto>getProduct(
        @RequestParam(required = false)String name,
        @RequestParam(required = false)String brand,
        @RequestParam(required = false)String deString,
        @RequestParam(defaultValue = "0")int page,
        @RequestParam(defaultValue = "10")int size){
          PageRequest pageRequest=PageRequest.of(page, size); 
          
          return myEntityService.searchProduct(name, deString, brand, pageRequest);
        }
}

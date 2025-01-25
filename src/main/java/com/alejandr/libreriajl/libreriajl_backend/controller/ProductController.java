package com.alejandr.libreriajl.libreriajl_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandr.libreriajl.libreriajl_backend.entity.Product;
import com.alejandr.libreriajl.libreriajl_backend.service.MyEntityService;

@RestController
public class ProductController {
    @Autowired
    private MyEntityService myEntityService;

@PostMapping("/saveProduct")
public void saveProduct(@RequestBody Product product){
    myEntityService.saveProduct(product);
}
@GetMapping("/getProduct")
public void getProduct(@RequestParam Long Id){
    myEntityService.getProduct(Id);
}
}

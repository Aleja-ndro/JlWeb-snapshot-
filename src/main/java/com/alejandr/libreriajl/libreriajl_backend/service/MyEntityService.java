package com.alejandr.libreriajl.libreriajl_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandr.libreriajl.libreriajl_backend.entity.Product;
import com.alejandr.libreriajl.libreriajl_backend.repository.MyProductRepo;

@Service
public class MyEntityService {
@Autowired
private MyProductRepo myProductRepo;
public void saveProduct(Product product){
    myProductRepo.save(product);
}
public Product getProduct(Long id){
    return myProductRepo.findById(id).orElse(null);
}
}

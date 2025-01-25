package com.alejandr.libreriajl.libreriajl_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandr.libreriajl.libreriajl_backend.entity.Product;

public interface MyProductRepo extends JpaRepository<Product,Long>{

}

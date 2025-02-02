package com.alejandr.libreriajl.libreriajl_backend.specification;


import org.springframework.data.jpa.domain.Specification;

import com.alejandr.libreriajl.libreriajl_backend.entity.Product;

import jakarta.persistence.criteria.Predicate;

public class ProductSpecifications {
    public static Specification<Product>filterProduct(String name, String brand,String description){
        return( root,query,cb)->{

            Predicate predicate=cb.conjunction();

            if(brand!=null && !brand.isEmpty()){
                predicate=cb.and(predicate,cb.like(cb.lower(root.get("brand")),"%"+brand.toLowerCase()+"%"));
            }
            if(name!=null &&!name.isEmpty()){
                predicate=cb.and(predicate,cb.like(cb.lower(root.get("name")),"%"+ name.toLowerCase()+"%"));
            }
            if(description!=null && !description.isEmpty()){
                predicate=cb.and(predicate,cb.like(cb.lower(root.get("description")),"%"+description.toLowerCase()+"%"));
                
            }
            return predicate;
        };
    }}

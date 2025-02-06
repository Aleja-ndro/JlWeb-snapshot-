package com.alejandr.libreriajl.libreriajl_backend.specification;


import org.springframework.data.jpa.domain.Specification;

import com.alejandr.libreriajl.libreriajl_backend.entity.Product;

import jakarta.persistence.criteria.Predicate;

public class ProductSpecifications {
    public static Specification<Product>filterProduct(String description, String marca,String rubro){
        return( root,query,cb)->{

            Predicate predicate=cb.conjunction();

            if(marca!=null && !marca.isEmpty()){
                predicate=cb.and(predicate,cb.like(cb.lower(root.get("marca")),"%"+marca.toLowerCase()+"%"));
            }
            if(description!=null &&!description.isEmpty()){
                predicate=cb.and(predicate,cb.like(cb.lower(root.get("description")),"%"+ description.toLowerCase()+"%"));
            }
            if(rubro!=null && !rubro.isEmpty()){
                predicate=cb.and(predicate,cb.like(cb.lower(root.get("rubro")),"%"+rubro.toLowerCase()+"%"));
                
            }
            return predicate;
        };
    }}

package com.alejandr.libreriajl.libreriajl_backend.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.module.ResolutionException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alejandr.libreriajl.libreriajl_backend.dto.ProductDto;
import com.alejandr.libreriajl.libreriajl_backend.entity.Product;
import com.alejandr.libreriajl.libreriajl_backend.service.MyEntityService;

@RestController
public class ProductController{
    @Autowired
    private MyEntityService myEntityService;

    @GetMapping("/products")
    public Page<ProductDto>getProduct(
        @RequestParam(required = false)String description,
        @RequestParam(required = false)String marca,
        @RequestParam(required = false)String rubro,
        @RequestParam(defaultValue = "0")int page,
        @RequestParam(defaultValue = "10")int size){

          PageRequest pageRequest=PageRequest.of(page, size); 
          
          return myEntityService.searchProduct(description, rubro, marca, pageRequest);
        }

        
        @PostMapping("/upload")
        public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
         try {
          myEntityService.loadProductsFromFile(file);
          return ResponseEntity.ok("Archivo procesado con exito");
         
                   
         } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al leer el archivo");
         }
        }
      }


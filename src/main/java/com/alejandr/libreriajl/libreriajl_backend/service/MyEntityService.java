package com.alejandr.libreriajl.libreriajl_backend.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alejandr.libreriajl.libreriajl_backend.dto.ProductDto;
import com.alejandr.libreriajl.libreriajl_backend.entity.Product;
import com.alejandr.libreriajl.libreriajl_backend.repository.MyProductRepo;
import com.alejandr.libreriajl.libreriajl_backend.specification.ProductSpecifications;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@Service
public class MyEntityService {
@Autowired
private MyProductRepo myProductRepo;

public Page<ProductDto>searchProduct(String description, String rubro,String marca, Pageable pageable){
    var specification= ProductSpecifications.filterProduct(description, marca, rubro);
    Page<Product>products= myProductRepo.findAll(specification, pageable);
    return products.map(product->new ProductDto(product.getCodigo(),product.getMarca(),product.getDescription(),product.getPrecioVenta()));
}
public void loadProductsFromFile(MultipartFile file) throws IOException{
    try(BufferedReader reader=new BufferedReader(new InputStreamReader(file.getInputStream(),StandardCharsets.UTF_8))){
        String line;
        
        
        while ((line=reader.readLine())!=null) {
            try{ 
            String[] data= line.split(";");
         
            
            Product product=new Product();
            product.setCodigo(data[0]);
            product.setRubro(data[1]);
            product.setDescription(data[2]);
            product.setMarca(data[3]);
            try{ 
            product.setPrecioCompra(Double.parseDouble(data[4]));
            product.setStock(Double.parseDouble(data[5]));
            product.setStockMinimo(Integer.parseInt(data[6]));
            product.setAlicuotaIva(Integer.parseInt(data[7]));
            product.setCodigo(data[9]);
            }catch(NumberFormatException e){
                System.out.println("Error en formato "+line);
                continue;

            }
            myProductRepo.save(product);
            myProductRepo.flush();
        }catch (Exception e){
            System.out.println("Procesando linea "+ line);
            e.printStackTrace();
        }
            
        }
    }catch(IOException e){
        System.out.println(" error"+e.getMessage());
        throw e;
    }
}}





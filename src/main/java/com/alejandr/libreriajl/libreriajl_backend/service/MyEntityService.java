package com.alejandr.libreriajl.libreriajl_backend.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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

public Page<ProductDto>searchProduct(String name, String description,String brand, Pageable pageable){
    var specification= ProductSpecifications.filterProduct(name, brand, description);
    Page<Product>products= myProductRepo.findAll(specification, pageable);
    return products.map(product->new ProductDto(product.getCodigo(),product.getMarca(),product.getDescripcion(),product.getPrecioVenta()));
}
public void loadProductsFromCsv(){
    String filePath="src/main/resources/util.libro1a.txt";
    try(CSVReader reader=new CSVReader(new FileReader(filePath))){
        List<String[]>rows=reader.readAll();
        for(String[]row:rows){
            Product product=new Product();
            product.setCodigo(row[0]);
            product.setRubro(row[1]);
            product.setDescripcion(row[2]);
            product.setMarca(row[3]);
            product.setPrecioCompra(Double.parseDouble(row[4]));
            product.setPrecioVenta(Double.parseDouble(row[5]));
            product.setStock(Integer.parseInt(row[6]));
            product.setStockMinimo(Integer.parseInt(row[7]));
            product.setAlicuotaIva(Integer.parseInt(row[8]));
            product.setCodigoProv((row[9]));

            myProductRepo.save(product);

        }
    }catch(IOException | CsvException e){
        e.printStackTrace();
    }
}

}
package com.alejandr.libreriajl.libreriajl_backend.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String codigo;
    private String descripcion;
    private String marca;
    private double precioVenta;
        
}

package com.alejandr.libreriajl.libreriajl_backend.entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    
    private String codigo; // El código es único, por eso es @Id
    private String rubro;
    private String description;
    private String marca;
    private double precioCompra;
    private double precioVenta;
    private double stock;
    private int stockMinimo;
    private int alicuotaIva;
    private String codigoProv;

}

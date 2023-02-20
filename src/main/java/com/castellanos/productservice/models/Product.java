package com.castellanos.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id;
    private String name;
    private double cost;
    private String size;
    private String description;
    private String photo;
    private int amount;

    public Product(String name, double cost, String size, String description, String photo, int amount) {
        this.name = name;
        this.cost = cost;
        this.size = size;
        this.description = description;
        this.photo = photo;
        this.amount = amount;
    }
}

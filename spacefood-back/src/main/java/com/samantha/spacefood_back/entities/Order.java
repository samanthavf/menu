package com.samantha.spacefood_back.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TABLE_ORDERS")
public class Order {
private Long id;
private List<Dish> pratos = new ArrayList<>();
private double valorTotal;
}

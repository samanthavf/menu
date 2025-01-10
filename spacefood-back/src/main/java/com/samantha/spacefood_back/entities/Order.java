package com.samantha.spacefood_back.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TABLE_ORDERS")
public class Order{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@ManyToMany
@JoinTable(
    name = "order_dishes",
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "dish_id")
)
List<Dish> pratos = new ArrayList<>();
List<String> pratosPedidos = new ArrayList<>();

@Column(name = "valor_total")
private double valorTotal= 0.0;
@Column(name = "mesa")
private int numeroMesa;


public void addDish(List<Dish> pratosEncontrados) {
	pratos.addAll(pratosEncontrados);
	double totalAdd = pratosEncontrados.stream()
			.mapToDouble(Dish::getPreco).sum();
	this.valorTotal += totalAdd;
	}


}

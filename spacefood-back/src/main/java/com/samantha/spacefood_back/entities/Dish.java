package com.samantha.spacefood_back.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TABLE_DISHES")
public class Dish {
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;
private String nome;
private String descricao;
private double preco;
private String imagem;

}

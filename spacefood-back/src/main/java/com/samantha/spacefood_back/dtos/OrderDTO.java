package com.samantha.spacefood_back.dtos;

import java.util.List;

import com.samantha.spacefood_back.entities.Dish;

public record OrderDTO(List<Dish> pratos,List<String> pratosPedidos,int numeroMesa) {

}

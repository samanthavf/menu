package com.samantha.spacefood_back.dtos;

import java.util.List;

import com.samantha.spacefood_back.entities.Dish;

public record OrderDTO(Long id, List<Dish> pratos, double valortotal) {

}

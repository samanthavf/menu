package com.samantha.spacefood_back.dtos;

import com.samantha.spacefood_back.entities.Dish;

public record CartRequestDTO(Dish prato, int quantidade) {

}

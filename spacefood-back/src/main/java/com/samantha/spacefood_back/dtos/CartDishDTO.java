package com.samantha.spacefood_back.dtos;

import com.samantha.spacefood_back.entities.Dish;

public record CartDishDTO(Dish prato, int quantidade) {}

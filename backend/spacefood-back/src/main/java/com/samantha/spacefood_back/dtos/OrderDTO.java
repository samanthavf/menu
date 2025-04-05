package com.samantha.spacefood_back.dtos;

import com.samantha.spacefood_back.entities.Cart;

public record OrderDTO(int numeroMesa,Cart carrinho) {

}

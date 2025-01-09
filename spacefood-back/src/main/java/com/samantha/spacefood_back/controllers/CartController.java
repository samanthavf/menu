package com.samantha.spacefood_back.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samantha.spacefood_back.dtos.CartRequestDTO;
import com.samantha.spacefood_back.entities.Cart;
import com.samantha.spacefood_back.entities.Dish;
import com.samantha.spacefood_back.exception.CartNotFoundException;
import com.samantha.spacefood_back.services.CartService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("cart")
@CrossOrigin("*")
public class CartController {
	private final CartService service;
	
	@PostMapping("/carrinho")
	public ResponseEntity<Cart> createCart() {
	    Cart novoCarrinho = service.createCart();
	    return new ResponseEntity<>(novoCarrinho, HttpStatus.CREATED);
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Cart> read(@PathVariable Long id){
		try {
			return ResponseEntity.ok(service.cart(id));
		} catch (CartNotFoundException e) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PostMapping("/carrinho/{cartId}")
	public ResponseEntity<Cart> cart(@PathVariable Long cartId, @RequestBody CartRequestDTO prato){
		return new ResponseEntity<>(service.addDish(cartId,prato), HttpStatus.CREATED);
	}
	
	@PutMapping("/remover/{cartId}")
	public ResponseEntity<Void> update(@PathVariable Long cartId,@RequestBody Dish prato) throws Exception{
			service.removeDish(cartId, prato);
			return ResponseEntity.ok().build();
	}
}

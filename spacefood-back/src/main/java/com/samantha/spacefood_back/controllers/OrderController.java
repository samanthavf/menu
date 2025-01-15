package com.samantha.spacefood_back.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samantha.spacefood_back.dtos.OrderDTO;
import com.samantha.spacefood_back.entities.Order;
import com.samantha.spacefood_back.services.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("order")
@CrossOrigin("*")
public class OrderController {
	private final OrderService service;
	
	@PostMapping("/fazer-pedido/{cartId}")
	public ResponseEntity<Order> create(@PathVariable Long cartId,@RequestBody OrderDTO dto){
		return new ResponseEntity<>(service.createOrder(cartId, dto), HttpStatus.CREATED);
	}
	
	@GetMapping("/pedido/{id}")
	public ResponseEntity<Order> getById(@PathVariable Long id){
		return ResponseEntity.ok(service.getById(id));
	}

	@GetMapping("/listar-pedidos")
	public ResponseEntity<Page<Order>> getAllOrders(Pageable pageable){
		return ResponseEntity.ok(service.getAllOrders(pageable));
	}
	
	@DeleteMapping("/cancelar-pedido/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
		service.deleteOrder(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}

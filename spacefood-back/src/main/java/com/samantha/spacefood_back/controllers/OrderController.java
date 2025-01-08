package com.samantha.spacefood_back.controllers;

import java.util.List;

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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("order")
@CrossOrigin("*")
public class OrderController {
	private final OrderService service;
	
	@PostMapping("/pedir")
	public ResponseEntity<Order> create(@RequestBody @Valid OrderDTO dto) throws Exception{
		return new ResponseEntity<>(service.createOrder(dto), HttpStatus.CREATED);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Order>> read(Pageable pageable){
		return ResponseEntity.ok(service.readOrders(pageable));
	}
	
	@DeleteMapping(path = "/remover/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception{
		service.cancelOrder(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}

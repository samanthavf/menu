package com.samantha.spacefood_back.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
public class OrderController {
	private final OrderService service;
	
	//Create
	public ResponseEntity<Order> create(@RequestBody @Valid OrderDTO dto){
		return new ResponseEntity<>(service.createOrder(dto), HttpStatus.CREATED);
	}
	
	//READ
	public ResponseEntity<Page<Order>> read(Pageable pageable){
		return ResponseEntity.ok(service.readOrders(pageable));
	}
	
	//DELETE
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.cancelOrder(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}

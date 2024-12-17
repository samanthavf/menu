package com.samantha.spacefood_back.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samantha.spacefood_back.dtos.DishDTO;
import com.samantha.spacefood_back.entities.Dish;
import com.samantha.spacefood_back.services.DishService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("dish")
public class DishController {
	private final DishService service;
	
	//CREATE
	@GetMapping("/adicionar")
	public ResponseEntity<Dish> create(@RequestBody @Valid DishDTO dto){
		return new ResponseEntity<>(service.createDish(dto), HttpStatus.CREATED);
	}
	//READ
	@GetMapping("/listar")
	public ResponseEntity<Page<Dish>> read (Pageable pageable){
		return ResponseEntity.ok(service.readDishes(pageable));
	}
	
	//REMOVE
	@DeleteMapping(path = "/remover/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//UPDATE
	@PutMapping("/atualizar")
	public ResponseEntity<Void> update(@RequestBody @Valid DishDTO dto) throws Exception{
		service.updateDish(dto);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}

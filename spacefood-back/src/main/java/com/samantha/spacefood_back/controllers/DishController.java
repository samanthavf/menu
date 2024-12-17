package com.samantha.spacefood_back.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samantha.spacefood_back.services.DishService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("dish")
public class DishController {
	private final DishService service;
	
}

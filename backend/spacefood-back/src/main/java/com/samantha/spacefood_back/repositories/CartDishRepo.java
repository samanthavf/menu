package com.samantha.spacefood_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samantha.spacefood_back.entities.CartDish;

public interface CartDishRepo extends JpaRepository<CartDish, Long> {

}

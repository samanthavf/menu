package com.samantha.spacefood_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.samantha.spacefood_back.entities.Cart;
import com.samantha.spacefood_back.entities.Dish;

public interface CartRepo  extends JpaRepository<Cart, Long>{

	@Query("SELECT d FROM Dish d WHERE d.nome IN :nome")
	Dish findByNameIn(@Param("nome") String prato);
	
	
}

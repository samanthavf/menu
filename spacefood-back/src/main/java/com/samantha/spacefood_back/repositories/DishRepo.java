package com.samantha.spacefood_back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.samantha.spacefood_back.entities.Dish;

@Repository
public interface DishRepo extends JpaRepository<Dish, Long>{
	
	@Query("SELECT d FROM Dish d WHERE d.nome IN :nome")
	List<Dish> findByNameIn(@Param("nome") List<String> nomePrato);
	
	@Query("SELECT d FROM Dish d WHERE d.id= :id")
	Optional<Dish> FindDishById(@Param("id")Long id);
	
	@Query("SELECT c FROM Dish c WHERE c.categoria= :categoria")
	List<Dish> findByCategory(@Param("categoria") String categoria);
	
	@Query("SELECT DISTINCT c.categoria FROM Dish c WHERE c.categoria IS NOT NULL")
	List<String> findAllCategories();

}

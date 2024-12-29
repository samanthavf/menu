package com.samantha.spacefood_back.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.samantha.spacefood_back.dtos.DishDTO;
import com.samantha.spacefood_back.entities.Dish;
import com.samantha.spacefood_back.repositories.DishRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DishService {
	
	private final DishRepo dishRepo;
	
	public Dish createDish(DishDTO dto) {
		Dish prato = new Dish(dto.id(), dto.nome(),
		dto.descricao(), dto.preco(), dto.imagem(), dto.categoria());
		return dishRepo.save(prato);
	}
	
	public Page<Dish> readDishes(Pageable pageable){
		return dishRepo.findAll(pageable);
	}
	
	public List<Dish> getCategory(String categoria) throws Exception{
		List<Dish> categorias = dishRepo.findByCategory(categoria);
		if (categorias.isEmpty()) {
			throw new Exception("Nenhum prato encontrado nesta categoria.");
		}
		return categorias;
	}
	
	public void delete(Long id) throws Exception {
		Optional<Dish> deleteDish = dishRepo.FindDishById(id);
		if (deleteDish.isPresent()) {
			dishRepo.deleteById(id);
		}else {
			throw new Exception("Não possível identificar prato.");
		}
}
	
	public void updateDish(DishDTO dto) throws Exception {
		Optional<Dish> findDish = dishRepo.findById(dto.id());
		
		if (findDish.isEmpty()) {
			throw new Exception("Prato não encontrado com o ID:" + dto.id());
		}else {
			Dish existingDish = findDish.get();
			existingDish.setNome(dto.nome());
			existingDish.setDescricao(dto.descricao());
			existingDish.setPreco(dto.preco());
			existingDish.setImagem(dto.imagem());
			dishRepo.save(existingDish);
		}
	}
	
}
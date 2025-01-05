package com.samantha.spacefood_back.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.samantha.spacefood_back.dtos.OrderDTO;
import com.samantha.spacefood_back.entities.Dish;
import com.samantha.spacefood_back.entities.Order;
import com.samantha.spacefood_back.repositories.DishRepo;
import com.samantha.spacefood_back.repositories.OrderRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService{
	private final OrderRepo orderRepo;
	private final DishRepo dishRepo;
	

	private List<Dish> getDish(List<String> nomesPratos) throws Exception {
		List<Dish> pratosEncontrados = dishRepo.findByNameIn(nomesPratos);
		if (pratosEncontrados.isEmpty()) {
			throw new Exception("Nenhum prato encontrado com os nomes fornecidos.");
		}
		return pratosEncontrados;
	}
	
	public Order createOrder(OrderDTO dto) throws Exception  {
		List<String> nomesDosPratos = dto.pratosPedidos();		
		List<Dish> pratosEncontrados = getDish(nomesDosPratos);
		
		
	Order pedido = new Order();
	pedido.addDish(pratosEncontrados);
	pedido.setNumeroMesa(dto.numeroMesa());
	
	return orderRepo.save(pedido);
	
	}
	
	@Transactional
	public List<Order> readOrders(Pageable pageable){
		List<Order> pegarPedidos = orderRepo.findAll();
		pegarPedidos.forEach(pedidos -> Hibernate.initialize(pedidos.getPratos()));
		return pegarPedidos;
	}
	
	public void cancelOrder(Long id) throws Exception {
		Optional<Order> findOrder = orderRepo.findOrderById(id);
		if (findOrder.isPresent()) {
			 orderRepo.deleteById(id);
		}else {
			throw new Exception("Não foi possível localizar pedido.");
		}
	}
	
}

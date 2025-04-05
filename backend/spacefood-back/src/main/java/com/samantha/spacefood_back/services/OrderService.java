package com.samantha.spacefood_back.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.samantha.spacefood_back.dtos.OrderDTO;
import com.samantha.spacefood_back.entities.Cart;
import com.samantha.spacefood_back.entities.Order;
import com.samantha.spacefood_back.exception.CartNotFoundException;
import com.samantha.spacefood_back.exception.OrderNotFoundException;
import com.samantha.spacefood_back.repositories.CartRepo;
import com.samantha.spacefood_back.repositories.OrderRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService{
	private final OrderRepo orderRepo;
	private final CartRepo cartRepo;

	public Order createOrder(Long id, OrderDTO dto){
      Cart carrinho = cartRepo.findById(id).orElseThrow(() -> 
      new CartNotFoundException("Carrinho não encontrado"));
		  
		  Order pedido = new Order();
		  pedido.setCarrinho(carrinho);
		  pedido.setNumeroMesa(dto.numeroMesa());
		  orderRepo.save(pedido);
			  
		   return pedido;
	}
	
	public Order getById(Long id) {
		return orderRepo.findById(id).orElseThrow(()->
		new OrderNotFoundException("Pedido não encontrado."));
	}
	
	public Page<Order> getAllOrders(Pageable pageable){
		return orderRepo.findAll(pageable);
	}
	
	public void deleteOrder(Long id) {
		Order pedido = orderRepo.findById(id).orElseThrow(() ->
		new OrderNotFoundException("Pedido não encontrado"));
		orderRepo.delete(pedido);
	}
	
}

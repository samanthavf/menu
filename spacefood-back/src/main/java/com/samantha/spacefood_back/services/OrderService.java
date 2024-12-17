package com.samantha.spacefood_back.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.samantha.spacefood_back.dtos.OrderDTO;
import com.samantha.spacefood_back.entities.Order;
import com.samantha.spacefood_back.repositories.OrderRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {
	private final OrderRepo orderRepo;
	
	//CRIAR PEDIDO
	public Order createOrder(OrderDTO dto) {
		Order pedido = new Order();
		pedido.setPratos(dto.pratos());
		pedido.setValorTotal(dto.valortotal());
		pedido.setNumeroMesa(dto.numeromesa());
		return orderRepo.save(pedido);
	}
	
	//LISTAR PEDIDOS
	public Page<Order> readOrders(Pageable pageable){
		return orderRepo.findAll(pageable);
	}
	
	//CANCELAR PEDIDO
	public void cancelOrder(Long id) {
        orderRepo.deleteById(id);
	}
	
}

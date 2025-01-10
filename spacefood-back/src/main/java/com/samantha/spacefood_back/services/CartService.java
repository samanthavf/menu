package com.samantha.spacefood_back.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.samantha.spacefood_back.dtos.CartRequestDTO;
import com.samantha.spacefood_back.entities.Cart;
import com.samantha.spacefood_back.entities.Dish;
import com.samantha.spacefood_back.exception.CartNotFoundException;
import com.samantha.spacefood_back.repositories.CartRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService {
	private final CartRepo cartRepo;
	
	@Transactional
	public Cart cart(Long id){
	  Optional<Cart> carrinho = cartRepo.findById(id);
	  if (carrinho.isPresent()) {
		  return carrinho.get();
	}
	  else {
		  throw new CartNotFoundException("Carrinho não encontrado.");
	}
	}
	
	public Cart createCart() {
	    Cart carrinho = new Cart();
	    carrinho.setPratoSelecionado(new ArrayList<>());
	    carrinho.setValorTotal(0.0);
	    return cartRepo.save(carrinho);
	}

	
	public Cart addDish(Long cartId,CartRequestDTO cart) {
		Optional<Cart> findCart = cartRepo.findById(cartId);
	    if (findCart.isEmpty()) {throw new RuntimeException("Carrinho não encontrado");}
		Cart carrinho = findCart.get();
		
		Optional<Dish> exitingDish = carrinho.getPratoSelecionado().stream()
				.filter(p -> p.getId().equals(cart.prato().getId())).findFirst();
		
		if (exitingDish.isPresent()) {
			Dish pratoExiste = exitingDish.get();
			
			double precoFinal = (pratoExiste.getPreco()*cart.quantidade())+carrinho.getValorTotal();
			carrinho.setValorTotal(precoFinal);
		}else {
			double precoFinal = (cart.prato().getPreco()*cart.quantidade())+carrinho.getValorTotal();
			carrinho.setValorTotal(precoFinal);
			
			carrinho.getPratoSelecionado().add(cart.prato());
		}
		
		return cartRepo.save(carrinho);
	}
	
	
	public void removeDish(Long cartId, CartRequestDTO prato) throws Exception {
		Optional<Cart> findCart = cartRepo.findById(cartId);
		
		if (findCart.isPresent()) {
			Cart carrinho = findCart.get();
			
			if (carrinho.getPratoSelecionado().contains(prato.prato())) {
				double precoRemovido = prato.prato().getPreco() * prato.quantidade();
				double precoFinal = carrinho.getValorTotal() - precoRemovido;
				
				carrinho.setValorTotal(Math.max(precoFinal, 0));
				
				carrinho.getPratoSelecionado().remove(prato.prato());
			    cartRepo.save(carrinho);		
			}else {
	            throw new Exception("Prato não encontrado no carrinho.");
	        }	
		}else {
			throw new CartNotFoundException("Carrinho indisponível.");
	}
}
}

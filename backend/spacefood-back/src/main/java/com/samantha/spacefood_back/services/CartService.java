package com.samantha.spacefood_back.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.samantha.spacefood_back.dtos.CartDishDTO;
import com.samantha.spacefood_back.entities.Cart;
import com.samantha.spacefood_back.entities.CartDish;
import com.samantha.spacefood_back.exception.CartNotFoundException;
import com.samantha.spacefood_back.repositories.CartDishRepo;
import com.samantha.spacefood_back.repositories.CartRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService {
	private final CartRepo cartRepo;
	private final CartDishRepo cartDishRepo;

	
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

	public void limparCarrinho(Long cartId) {
	    cartRepo.findById(cartId).ifPresentOrElse(carrinho -> {
	    	carrinho.getPratoSelecionado().clear();
	    	carrinho.setValorTotal(0);
	    	cartRepo.save(carrinho);},()-> {throw new RuntimeException("Carrinho não encontrado");});
	}
	
	public Cart addDish(Long cartId,CartDishDTO cart) {
		Optional<Cart> findCart = cartRepo.findById(cartId);
	    if (findCart.isEmpty()) {throw new RuntimeException("Carrinho não encontrado");}
		Cart carrinho = findCart.get();
		
		Optional<CartDish> pratoExiste = carrinho.getPratoSelecionado().stream()
				.filter(dish -> dish.getPrato().getId().equals(cart.prato().getId())).findFirst();
		
		if (pratoExiste.isPresent()) {
			throw new RuntimeException("Prato já está no carrinho");
		}else {
			CartDish novoPrato = new CartDish();
			novoPrato.setPrato(cart.prato());
			novoPrato.setQuantidade(cart.quantidade());
			
			cartDishRepo.save(novoPrato);
			carrinho.getPratoSelecionado().add(novoPrato);
			
			double novoValor = novoPrato.getQuantidade()*cart.prato().getPreco();
			carrinho.setValorTotal(carrinho.getValorTotal() + novoValor);
		}
		return cartRepo.save(carrinho);
	}
	
	
	public void removeDish(Long cartId, CartDishDTO prato) throws Exception {
		Optional<Cart> findCart = cartRepo.findById(cartId);
		if (findCart.isEmpty()) {throw new RuntimeException("Carrinho não encontrado");}
		Cart carrinho = findCart.get();
		
		CartDish pegarPrato = carrinho.getPratoSelecionado().stream()
				.filter(p -> p.getPrato().getId().equals(prato.prato().getId())).findAny()
				.orElseThrow(() -> new Exception("Prato não encontrado no carrinho: " + prato.prato()));
				
		if (pegarPrato.getQuantidade()!=prato.quantidade()){
			throw new Exception("Quantidade invalida de pratos.");
			
		}
		
	    double precoRemovido = pegarPrato.getPrato().getPreco() * prato.quantidade();
	    double novoValorTotal = carrinho.getValorTotal() - precoRemovido;
	    carrinho.setValorTotal(Math.max(novoValorTotal, 0));

	    carrinho.getPratoSelecionado().remove(pegarPrato);
	    cartRepo.save(carrinho);
	    cartDishRepo.delete(pegarPrato);
	}
	
}

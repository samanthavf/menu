import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Dish } from '../model/Dishes';
import { MenuService } from '../services/menu.service';
import { Router } from '@angular/router';
import { CartDishDTO } from '../model/CartDishDTO';
import { Cart } from '../model/Cart';
import { order } from '../model/Order';
import { error } from 'console';

@Component({
  selector: 'app-carrinho',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule
  ],
  templateUrl: './carrinho.component.html',
  styleUrl: './carrinho.component.css'
})

export class CarrinhoComponent{
constructor(private router:Router, private service:MenuService){}

cart = new Cart();
dish = new Dish();
pedido = new order(this.cart);

ngOnInit(): void{
  this.getOrders();
}

getOrders(){
  this.service.getCart().subscribe({
    next: (retorno) =>{
      this.cart = retorno;
    }, error: (error) => {
      console.log('Erro ao buscar pratos no carrinho', error);
    }
  }
  );
}

remover(prato:CartDishDTO) {
  this.service.removeToCart(prato).subscribe({
    next:(retorno)=>{
      console.log('Prato removido com sucesso.', retorno);
      try {
        this.getOrders();
      } catch (error) {
        console.log('Erro ao atualizar lista de pratos', error)
        location.reload();
      }
    },error: (error)=>{
      console.log('Erro ao remover prato', error);
    }
  });
  }

  order(pedidos:order) {
    this.service.addOrder(pedidos).subscribe({
      next:(retorno)=>{
        console.log("Pedido finalizado.", retorno);
      },
      error:(error)=>{
        console.log("Erro ao fazer pedido.", error);
      }
    });
    }

back() {
  this.router.navigate(['/menu'])
  }

}

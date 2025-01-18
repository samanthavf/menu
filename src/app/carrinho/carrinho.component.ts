import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MenuService } from '../services/menu.service';
import { Router } from '@angular/router';
import { CartDishDTO } from '../model/CartDishDTO';
import { Cart } from '../model/Cart';
import { order } from '../model/Order';

declare var bootstrap: any;

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

mensagemModal: string = 'pratos: ';
cart = new Cart();
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


  openModal(){
    const modal = new bootstrap.Modal(document.getElementById('pedidoModal'));
    modal.show();
  }
  

  order(pedidos:order) {
    this.service.addOrder(pedidos).subscribe({
      next:(retorno)=>{
        console.log("Pedido finalizado.", retorno);
      },
      error:(error)=>{
        console.log("Erro ao finalizar pedido.", error);
      }
    });
    }

    confirmarPedido(carrinho:Cart, pedido:order) {
    this.service.clearCart(carrinho).subscribe({
      next:()=>{
        console.log('Pedido confirmado.')   
        this.mensagemModal = 'Pedido enviado.';
        this.order(pedido);
        this.getOrders();
      },
      error:(error)=>{
        console.log('Erro ao confirmar pedido.', error)
      }
    })
}

back() {this.router.navigate(['/menu'])}

}

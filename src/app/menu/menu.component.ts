import { Component } from '@angular/core';
import { MenuService } from '../services/menu.service';
import { Dish, DishesByCategory } from '../model/Dishes';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CartDishDTO } from '../model/CartDishDTO';
import { Cart } from '../model/Cart';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule
  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})

export class MenuComponent {
constructor(private service:MenuService, private router:Router){}
pratosPorCategoria: DishesByCategory = {};
pratos = new Dish()

alterarQuantidade(novaQuantidade:number,pratoSelecionado:Dish): void {
  pratoSelecionado.quantidade = 1;
  if (novaQuantidade >= 1) {
    pratoSelecionado.quantidade = novaQuantidade;
  }
  return;
}

ngOnInit(): void {
  this.getDishes(); 
}

getDishes(){
  this.service.getAll().subscribe({
    next: (retorno)=>{
      this.pratosPorCategoria = retorno;
    },
    error: (error)=>{
      console.log('Erro ao buscar pratos:', error);
    }}
  );
} 

addCarrinho(prato:Dish) {
  if (prato.quantidade > 0) {
    const cartDTO = new CartDishDTO(prato, prato.quantidade);
    this.service.addToCart(cartDTO).subscribe({
      next:(retorno)=>{
        console.log('Prato adicionado ao carrinho:', retorno);
      },error:(error)=>{
        console.error('Erro ao adicionar prato ao carrinho', error);
      }
    }) 
  }else{
    console.warn('A quantidade do prato deve ser maior que 0 para adicionar ao carrinho.');
  }
}

carrinho() {
  this.router.navigate(['/carrinho'])
  }
  
}

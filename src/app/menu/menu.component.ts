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
pratosCarrinho: CartDishDTO[] = [];

alterarQuantidade(novaQuantidade:number,pratoSelecionado:Dish): void {
     const item = this.pratosCarrinho.find((dto) => dto.prato.id === pratoSelecionado.id);
  if (item) {
    if (novaQuantidade >= 1 || novaQuantidade <= 20) {
      item.quantidade = novaQuantidade;
    }
  }
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
  const cartDTO = new CartDishDTO(prato, prato.quantidade);
  this.service.addToCart(cartDTO).subscribe({
    next:(retorno)=>{
      console.log('Prato adicionado ao carrinho:', retorno);
    },error:(error)=>{
      console.error('Erro ao adicionar prato ao carrinho', error);
    }
  })
  }

carrinho() {
  this.router.navigate(['/carrinho'])
  }
  
}

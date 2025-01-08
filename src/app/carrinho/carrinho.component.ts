import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Dish } from '../model/Dishes';
import { MenuService } from '../services/menu.service';
import { Router } from '@angular/router';
import { Cart } from '../model/Cart';

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

prato = new Cart();
pratos:Cart[]=[];

ngOnInit(): void{
  this.getOrders();
}

getOrders(){
  this.service.getOrders().subscribe({
    next: (retorno) =>{
      this.prato = retorno;
    }, error: (error) => {
      console.log('Erro ao buscar pratos no carrinho', error);
    }
  }
  );
}

remover(_t18: Dish) {
  throw new Error('Method not implemented.');
  }
}

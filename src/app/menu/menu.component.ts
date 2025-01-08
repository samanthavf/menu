import { Component } from '@angular/core';
import { MenuService } from '../services/menu.service';
import { Dish, DishesByCategory } from '../model/Dishes';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { order } from '../model/Order';

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

results: any[] = [];
pedido = new order();
prato: Dish[]=[];


pratosPorCategoria: DishesByCategory = {};

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
  this.service.addToCart(prato).subscribe({
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

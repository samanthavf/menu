import { Component } from '@angular/core';
import { MenuService } from '../services/menu.service';
import { Dish, DishesByCategory } from '../model/Dishes';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

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

constructor(private service:MenuService){}

results: any[] = [];
prato = new Dish();
pratos: Dish[] = [];

pratosPorCategoria: DishesByCategory = {};

ngOnInit(): void {
  this.getDishes(); // Chamar ao inicializar o componente
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

addToCart(prato: Dish) {
  console.log('Prato adicionado ao carrinho:', prato);
  // Lógica para adicionar ao carrinho
}
}

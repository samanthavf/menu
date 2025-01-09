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

remover(prato: Dish) {
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

back() {
  this.router.navigate(['/menu'])
  }

}

import { Component } from '@angular/core';
import { MenuService } from '../services/menu.service';
import { Dishes } from '../model/Dishes';
import { error } from 'console';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {
constructor(private service:MenuService){}

pratos: Dishes[] = [];

getDishes(){
  this.service.getAll().subscribe(
    (retorno)=>{
      this.pratos = retorno;
    },
    (error)=>{
      console.log('Erro ao buscar pratos:', error);
    }
  );
}

}

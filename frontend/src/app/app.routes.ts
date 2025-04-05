import {Routes } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { CarrinhoComponent } from './carrinho/carrinho.component';

export const routes: Routes = [
    { path: '', redirectTo: '/menu', pathMatch: 'full' },
    { path: 'menu', component: MenuComponent },
    { path: 'carrinho', component: CarrinhoComponent },
    { path: '**', redirectTo: '/menu' },
];
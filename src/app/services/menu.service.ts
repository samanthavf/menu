import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Dish, DishesByCategory } from "../model/Dishes";
import { Injectable } from "@angular/core";
import { order } from "../model/Order";
import { Cart } from "../model/Cart";


@Injectable({
    providedIn: 'root', 
  })

export class MenuService{
    constructor(private http:HttpClient) {}

    private urlcategoria:string='http://localhost:8080/dish/categorias/comidas'
    getAll(): Observable<DishesByCategory>{
        return this.http.get<DishesByCategory>(this.urlcategoria);
    }

    
    private urlListarPratos:string = 'http://localhost:8080/cart/listar/1'
    getOrders():Observable<Cart>{
        return this.http.get<Cart>(this.urlListarPratos);
    }

    private urlAdicionarCart:string='http://localhost:8080/cart/carrinho/1'
    addToCart(prato: Dish): Observable<Cart>{
        return this.http.post<Cart>(this.urlAdicionarCart, prato);
    }
}
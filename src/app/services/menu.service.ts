import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Dish, DishesByCategory } from "../model/Dishes";
import { Injectable } from "@angular/core";
import { CartDishDTO } from "../model/CartDishDTO";
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
    getCart():Observable<Cart>{
        return this.http.get<Cart>(this.urlListarPratos);
    }

    private urlAdicionarCart:string='http://localhost:8080/cart/carrinho'
    addToCart(prato:CartDishDTO): Observable<any>{
        return this.http.post<CartDishDTO>(`${this.urlAdicionarCart}/${1}`, prato);
    }

    private urlremoverPrato:string='http://localhost:8080/cart/remover'
    removeToCart(prato:CartDishDTO):Observable<any>{
        return this.http.put<CartDishDTO>(`${this.urlremoverPrato}/${1}`, prato);
    }
}
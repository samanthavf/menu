import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { DishesByCategory } from "../model/Dishes";
import { Injectable } from "@angular/core";
import { CartDishDTO } from "../model/CartDishDTO";
import { Cart } from "../model/Cart";
import { order } from "../model/Order";

@Injectable({
    providedIn: 'root', 
  })

export class MenuService{
    constructor(private http:HttpClient) {}

    private urlcategoria:string='http://localhost:8080/dish/categorias/comidas'
    getAll(): Observable<DishesByCategory>{return this.http.get<DishesByCategory>(this.urlcategoria);}

    private urlListarPratos:string = 'http://localhost:8080/cart/listar/1'
    getCart():Observable<Cart>{return this.http.get<Cart>(this.urlListarPratos);}

    private urlAdicionarCart:string='http://localhost:8080/cart/carrinho'
    addToCart(prato:CartDishDTO): Observable<any>{return this.http.post<CartDishDTO>(`${this.urlAdicionarCart}/${1}`, prato);}
    
    private urlLimparCart:string = 'http://localhost:8080/cart/limpar'
    clearCart(cart:Cart):Observable<void>{return this.http.put<void>(`${this.urlLimparCart}/${1}`, cart)}

    private urlremoverPrato:string='http://localhost:8080/cart/remover'
    removeToCart(prato:CartDishDTO):Observable<any>{return this.http.put<CartDishDTO>(`${this.urlremoverPrato}/${1}`, prato);}

    private urlAdicionarPedido:string='http://localhost:8080/order/fazer-pedido/1'
    addOrder(pedido:order):Observable<order>{return this.http.post<order>(this.urlAdicionarPedido , pedido);}

    private urlPegarPedido:string='http://localhost:8080/order/pedido'
    getOrder(pedido:order):Observable<order>{return this.http.get<order>(`${this.urlPegarPedido}/${pedido.id}`);}
}
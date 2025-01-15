import { Cart } from "./Cart";

export class order{
    id: number=0;
    cart:Cart;
    numeroMesa: number=0;

    constructor(carrinho:Cart){
        this.cart=carrinho;
    }
} 
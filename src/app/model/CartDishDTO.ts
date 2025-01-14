import { Dish } from "./Dishes";

export class CartDishDTO{
  id:number=0;
  prato: Dish;
  quantidade: number = 1 ;

  constructor(prato: Dish, quantidade:number) {
    this.prato = prato,
    this.quantidade= quantidade;
  }
}
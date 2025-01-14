import { Dish } from "./Dishes";

export class CartDishDTO{
  prato: Dish;
  quantidade: number = 0 ;

  constructor(prato: Dish, quantidade:number) {
    this.prato = prato,
    this.quantidade= quantidade;
  }
}
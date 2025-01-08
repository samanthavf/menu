import { Dish } from "./Dishes";

export class order{
    id: number=0;
    pratos: Dish[] = [];
    pratosPedidos: string[] = [];
    valorTotal: number= 0;
    numeroMesa: number=0;

} 
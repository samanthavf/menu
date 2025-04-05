import { CartDishDTO } from "./CartDishDTO";

export class Cart{
    id:number=0;
    pratoSelecionado:CartDishDTO[]= [];
    valorTotal:number=0;
}

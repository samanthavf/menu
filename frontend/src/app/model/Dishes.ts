export class Dish{
 id: number = 0;
 nome: string= '';
 descricao: string= '';
 preco: number= 0;
 imagem: string= '';
 categoria: string= '';
 quantidade:number=0;
}

export interface DishesByCategory {
    [key: string]: Dish[]; 
  }


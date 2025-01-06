export class Dish{
 id: number = 0;
 nome: string= '';
 descricao: string= '';
 preco: number= 0;
 imagem: string= '';
 categoria: string= '';
}

export interface DishesByCategory {
    [key: string]: Dish[]; // Exemplo: "Massas": Dish[], "Sobremesas": Dish[]
  }
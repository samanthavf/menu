import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Dishes } from "../model/Dishes";
import { Injectable } from "@angular/core";


@Injectable({
    providedIn: 'root', 
  })

export class MenuService{
    constructor(private http:HttpClient) {}

    private urlcategoria:string='http://localhost:8080/dish/categorias/comidas'

    getAll(): Observable<Dishes[]>{
        return this.http.get<Dishes[]>(this.urlcategoria);
    }

}
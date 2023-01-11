import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddShelf } from 'src/models/shelfDTO/addShelf';
import { ResponseShelf } from 'src/models/shelfDTO/responseShelfDTO';

@Injectable({
  providedIn: 'root'
})
export class ShelfService {

  readonly shelfsUrl = "http://localhost:7777/shelfs";
  constructor(private httpClient: HttpClient) { }

  public getAllShelfs():Observable<any>{
    return this.httpClient.get<any>(`${this.shelfsUrl}/pharmacy`,{withCredentials:true});
  }
  public getById(shelfId:number):Observable<any>{
     return this.httpClient.get<ResponseShelf>(`${this.shelfsUrl}/`+shelfId,{withCredentials:true});
  }
  public addShelf(addShelf:AddShelf):Observable<any>{
    return this.httpClient.post<ResponseShelf>(`${this.shelfsUrl}`,addShelf,{withCredentials:true});
  }
  public delete(shelfId:number):Observable<any>{
    return this.httpClient.delete<any>(`${this.shelfsUrl}/`+shelfId,{withCredentials:true});
  }
}

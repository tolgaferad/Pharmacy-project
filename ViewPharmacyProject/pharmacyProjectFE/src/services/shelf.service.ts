import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShelfService {

  readonly shelfsUrl = "http://localhost:7777/shelfs";
  constructor(private httpClient: HttpClient) { }

  public getAllShelfs():Observable<any>{
    return this.httpClient.get<any>(`${this.shelfsUrl}/pharmacy`,{withCredentials:true});
  }
  // public getByShelf(shelfId:number):Observable<any>{
  //   return this.httpClient.get<AddMedicine>(`${this.pharmacyUrl}/shelfs/`+shelfId,{withCredentials:true});
  // }
  // public getByPharmacy():Observable<any>{
  //   return this.httpClient.get<any>(`${this.pharmacyUrl}/pharmacy/`,{withCredentials:true});
  // }
  // public addToShelf(medicineId:number, shelfId:number):Observable<any>{
  //   return this.httpClient.post<any>(`${this.pharmacyUrl}/`+medicineId+'/shelfs/'+shelfId,{withCredentials:true});
  // }
  // public editMedicine(medicineId:number,editMedicine:EditMedicine){
  //   return this.httpClient.put<EditMedicine>(`${this.pharmacyUrl}/`+medicineId,editMedicine,{withCredentials:true})
  // }
  // public deleteMedicine(medicineId:number):Observable<any>{
  //   return this.httpClient.delete<any>(`${this.pharmacyUrl}/`+medicineId,{withCredentials:true});
  // }
}

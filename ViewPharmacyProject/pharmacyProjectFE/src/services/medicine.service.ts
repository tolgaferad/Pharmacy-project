import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddMedicine } from 'src/models/medicineDTO/addMedicine';
import { EditMedicine } from 'src/models/medicineDTO/editMedicine';
import { ResponseMedicine } from 'src/models/medicineDTO/responseMedicine';

@Injectable({
  providedIn: 'root'
})
export class MedicineService {
  readonly pharmacyUrl = "http://localhost:7777/medicines";
  constructor(private httpClient: HttpClient) { }

  public addMedicine(medicine:AddMedicine):Observable<any>{
    return this.httpClient.post<AddMedicine>(`${this.pharmacyUrl}`,medicine,{withCredentials:true});
  }
  public getByShelf(shelfId:number):Observable<any>{
    return this.httpClient.get<AddMedicine>(`${this.pharmacyUrl}/shelfs/`+shelfId,{withCredentials:true});
  }
  public getByPharmacy():Observable<any>{
    return this.httpClient.get<any>(`${this.pharmacyUrl}/pharmacy/`,{withCredentials:true});
  }
  public addToShelf(medicineId:number, shelfId:number):Observable<any>{
    return this.httpClient.get<any>(`${this.pharmacyUrl}/`+medicineId+'/shelfs/'+shelfId,{withCredentials:true});
  }
  public editMedicine(medicineId:number,editMedicine:EditMedicine){
    return this.httpClient.put<EditMedicine>(`${this.pharmacyUrl}/`+medicineId,editMedicine,{withCredentials:true})
  }
  public deleteMedicine(medicineId:number):Observable<any>{
    return this.httpClient.delete<any>(`${this.pharmacyUrl}/`+medicineId,{withCredentials:true});
  }
}

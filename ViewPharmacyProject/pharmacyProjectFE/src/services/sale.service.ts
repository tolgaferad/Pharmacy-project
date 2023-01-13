import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddSale } from 'src/models/saleDTO/addSale';
import { ResponseSale } from 'src/models/saleDTO/responseSale';

@Injectable({
  providedIn: 'root'
})
export class SaleService {
  readonly saleUrl = "http://localhost:7777/sales";
  constructor(private httpClient: HttpClient) { }

  public addSale(sale:AddSale):Observable<any>{
    return this.httpClient.post<AddSale>(`${this.saleUrl}`,sale,{withCredentials:true});
  }
  public addMedicineToSale(medicineId:number, saleId:number):Observable<any>{
    return this.httpClient.get<any>(`${this.saleUrl}/`+saleId+'/medicines/'+medicineId,{withCredentials:true});
  }
  public getAllByPharmacy():Observable<any>{
    return this.httpClient.get<any>(`${this.saleUrl}/`,{withCredentials:true})
  }
  public deleteSale(saleId:number):Observable<any>{
    return this.httpClient.delete<any>(`${this.saleUrl}/`+saleId,{withCredentials:true});
  }
  public confirmSale(saleId:number):Observable<any>{
    return this.httpClient.get<any>(`${this.saleUrl}/`+saleId+'/confirm',{withCredentials:true});
  }
  public getById(saleId:number):Observable<any>{
    return this.httpClient.get<any>(`${this.saleUrl}/`+saleId,{withCredentials:true});
  }
 
}

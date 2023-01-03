import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Pharmacy } from 'src/models/pharmacyDTO/pharmacy';
import { UserOnlyEmail } from 'src/models/userDTO/userOnlyEmail';

@Injectable({
  providedIn: 'root'
})
export class PharmacyService {

  readonly pharmacyUrl = "http://localhost:7777/pharmacy";
  constructor(private httpClient: HttpClient) { }

  public createPharmacy(pharmacy:Pharmacy):Observable<any>{
    return this.httpClient.post<Pharmacy>(`${this.pharmacyUrl}`,pharmacy,{withCredentials:true});
  }
  public addPharmacist(pharmacist:UserOnlyEmail):Observable<any>{
    return this.httpClient.post<UserOnlyEmail>(`${this.pharmacyUrl}/pharmacist`,pharmacist,{withCredentials: true});
  }
  public deletePharmacistFromPharmacy(pharmacistId:Number):Observable<any>{
    return this.httpClient.delete<any>(`${this.pharmacyUrl}/pharmacist/`+pharmacistId,{withCredentials:true});
  }
  public getAllPharmacists():Observable<any>{
    return this.httpClient.get<any>(`${this.pharmacyUrl}/pharmacist`,{withCredentials:true});
  }
  public editPharmacy(pharmacy:Pharmacy):Observable<any>{
    return this.httpClient.put<any>(`${this.pharmacyUrl}`,pharmacy,{withCredentials:true});
  }
}

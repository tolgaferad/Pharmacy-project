import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import {User} from '../models/userDTO/user';
import {UserLogin} from '../models/userDTO/userlogin.model';
import {UserRegister} from '../models/userDTO/userRegister.model';
import { Router } from '@angular/router';
import { catchError, retry } from 'rxjs';
import { UserEdit } from 'src/models/userDTO/userEdit';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  readonly userUrl = "http://localhost:7777/users";
  constructor(private httpClient: HttpClient) { }

  public register(userRegister:UserRegister):Observable<UserRegister>{
    return this.httpClient.post<UserRegister>(`${this.userUrl}`,userRegister);
  }
  
  public login(userLogin:UserLogin):Observable<any>{
    return this.httpClient.post<UserLogin>(`${this.userUrl}/login`,userLogin,{withCredentials: true});
    // .pipe(retry(1),catchError(this.handleError));
  }
  public getUser():Observable<any>{
    return this.httpClient.get<any>(`${this.userUrl}/`,{withCredentials:true});
  }
  public editUser(userEdit:UserEdit):Observable<any>{
    return this.httpClient.put<any>(`${this.userUrl}/edit`,userEdit,{withCredentials:true});
  }
 
}

import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/services/user.service';
import { Observable } from 'rxjs';
import { UserLogin } from 'src/models/userDTO/userlogin.model';
import { User } from 'src/models/userDTO/user';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-user',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  routeId!:number;
  errorMsg!:any;
  errors=[];
  cookieValue!:string;
  constructor(private userService:UserService,
                      private router: Router,
                      private route:ActivatedRoute,private httpClient:HttpClient,
                ) { 
    }
    user: User = {
      id: 0,
      name: '',
      username: '',
      email: '',
      password: '',
      createTime:'',
    }
    userlogin: UserLogin = {
      username: '',
      password: ''
    }
    hasErrorInvalid: boolean = false;
    errorMessage!:string;
    ngOnInit(): void {
    
    }
  onSubmit():void{
    this.userService.login(this.userlogin).subscribe(
      response => {
        this.router.navigate(['/main/']);//TODO main page
        console.log(response);
      },
      err => {
        console.log(err);
        this.hasErrorInvalid=true;
      }
    )
  }

}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/services/user.service';
import { UserRegister } from 'src/models/userDTO/userRegister.model';
@Component({
  selector: 'app-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  constructor(private userService:UserService,
              private router: Router) { }
  user: UserRegister = {
    id:0,
    name:'',
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
    createTime: '',
    role:''
  }
  ngOnInit(): void {
  }
  hasErrorForExistingMail: boolean = false;
  hasErrorEmailForInvalidFormat: boolean = false;
  hasErrorForMandatoryEmail: boolean=false;
  hasErrorName: boolean = false;
  hasErrorUsername: boolean = false;
  hasErrorPassword: boolean = false;
  hasErrorConfirmPassword: boolean = false;
  hasErrorForExistingUsername: boolean=false;
  hasErrorForRole: boolean=false;

  onSubmit() {
    this.userService.register(this.user).subscribe(
      response=>{
        this.router.navigate(['/login']);
      },
      err=>{
        this.hasErrorForExistingMail = false;
        this.hasErrorEmailForInvalidFormat = false;
        this.hasErrorForMandatoryEmail=false;
        this.hasErrorName = false;
        this.hasErrorUsername = false;
        this.hasErrorPassword = false;
        this.hasErrorConfirmPassword = false;
        this.hasErrorForExistingUsername=false;
        this.hasErrorForRole
        console.log(err);
        
        if (err.error!= null) {
          
          if (err.error.email != null) {
            let emailErrorMessage=err.error.email;
            if(emailErrorMessage=='Invalid email format'){
              this.hasErrorEmailForInvalidFormat = true;
            }
            else{
              this.hasErrorForMandatoryEmail=true;
            }
             
          }
          if(err.error.msg=='Email already exists'){
            this.hasErrorForExistingMail = true;
          }
          if(err.error.msg=='Username already exists'){
            this.hasErrorForExistingUsername = true;
          }
          if(err.error.msg=='Role should be either ADMIN or USER'){
            this.hasErrorForRole=true;
          }
          
          if (err.error.firstName != null) {
            this.hasErrorName = true; 
          }
          if (err.error.username != null) {
            this.hasErrorUsername = true; 
          }
          if (err.error.password != null) {
            this.hasErrorPassword = true; 
          }
          if (err.error.confirmPassword != null) {
            this.hasErrorConfirmPassword = true; 
          }
          if(err.error.role!=null){
            this.hasErrorForRole=true;
          }
      }
    }
    )

  }
}

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { MainComponent } from './main/main.component';


const routes: Routes = [
  {path:'',
   redirectTo:'login', pathMatch:'full'},
  {path:'login',
  component:UserLoginComponent},
  {path:'register',
   component:UserRegisterComponent},
   {path:'main',
    component:MainComponent},
   
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

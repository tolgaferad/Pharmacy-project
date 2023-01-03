import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { MainComponent } from './main/main.component';
import { PharmacyComponent } from './pharmacy/pharmacy.component';
import { ShelfsComponent } from './shelfs/shelfs.component';
import { SalesComponent } from './sales/sales.component';
import { MedicinesComponent } from './medicines/medicines.component';
import { UserComponent } from './user/user.component';


const routes: Routes = [
  {path:'',
   redirectTo:'login', pathMatch:'full'},
  {path:'login',
  component:UserLoginComponent},
  {path:'register',
   component:UserRegisterComponent},
  {path:'main',
  component:MainComponent},
  {path:'pharmacy',
  component:PharmacyComponent},
  {path:'shelfs',
  component:ShelfsComponent},
  {path:'sales',
  component:SalesComponent},
  {path:'medicines',
  component:MedicinesComponent},
  {path:'user',
  component:UserComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

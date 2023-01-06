import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { HttpClientModule } from '@angular/common/http';
import { MainComponent } from './main/main.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { MatTableModule } from '@angular/material/table';
import { PharmacyComponent } from './pharmacy/pharmacy.component';
import { MedicinesComponent } from './medicines/medicines.component';
import { SalesComponent } from './sales/sales.component';
import { ShelfsComponent } from './shelfs/shelfs.component';
import { UserComponent } from './user/user.component';
import {MatButtonModule} from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


@NgModule({
  declarations: [
    AppComponent,
    UserLoginComponent,
    UserRegisterComponent,
    MainComponent,
    HeaderComponent,
    FooterComponent,
    PharmacyComponent,
    MedicinesComponent,
    SalesComponent,
    ShelfsComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatTableModule,
    MatButtonModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

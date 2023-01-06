import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import {MatDialogModule} from '@angular/material/dialog';
import { CreatePharmDialogComponent } from './create-pharm-dialog/create-pharm-dialog.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';


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
    UserComponent,
    CreatePharmDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatTableModule,
    MatButtonModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

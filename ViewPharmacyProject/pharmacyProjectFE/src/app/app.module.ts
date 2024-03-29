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
import { AddPharmacistDialogComponent } from './add-pharmacist-dialog/add-pharmacist-dialog.component';
import { EditPharmacyDialogComponent } from './edit-pharmacy-dialog/edit-pharmacy-dialog.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import { AddMedicinesDialogComponent } from './add-medicines-dialog/add-medicines-dialog.component';
import { AddMedicineToShelfDialogComponent } from './add-medicine-to-shelf-dialog/add-medicine-to-shelf-dialog.component';
import {MatDatepicker, MatDatepickerModule} from '@angular/material/datepicker';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon';
import { AddShelfDialogComponent } from './add-shelf-dialog/add-shelf-dialog.component';
import { ViewShelfMedicinesDialogComponent } from './view-shelf-medicines-dialog/view-shelf-medicines-dialog.component';
import { CreateSaleDialogComponent } from './create-sale-dialog/create-sale-dialog.component';
import { AddMedicineToSaleDialogComponent } from './add-medicine-to-sale-dialog/add-medicine-to-sale-dialog.component';
import { SnackBarMessageForAddMedToSaleComponent } from './snack-bar-message-for-add-med-to-sale/snack-bar-message-for-add-med-to-sale.component';



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
    CreatePharmDialogComponent,
    AddPharmacistDialogComponent,
    EditPharmacyDialogComponent,
    AddMedicinesDialogComponent,
    AddMedicineToShelfDialogComponent,
    AddShelfDialogComponent,
    ViewShelfMedicinesDialogComponent,
    CreateSaleDialogComponent,
    AddMedicineToSaleDialogComponent,
    SnackBarMessageForAddMedToSaleComponent,
    
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
    ReactiveFormsModule,
    MatPaginatorModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatIconModule,
    MatSnackBarModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

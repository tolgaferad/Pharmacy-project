import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AddMedicine } from 'src/models/medicineDTO/addMedicine';
import { MedicineService } from 'src/services/medicine.service';
import { MatDatepicker,MatDatepickerModule } from '@angular/material/datepicker';
import { FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-medicines-dialog',
  templateUrl: './add-medicines-dialog.component.html',
  styleUrls: ['./add-medicines-dialog.component.css']
})
export class AddMedicinesDialogComponent implements OnInit {
  medicine:AddMedicine={
    name:'',
    strength:'',
    manufacturer:'',
    details:'',
    price:'',
    expiryDate:'',
    quantity:0,
}
  constructor(private medicineService:MedicineService,
              public dialog: MatDialog,
              private router: Router,
              public dialogRef: MatDialogRef<AddMedicinesDialogComponent>) { }

  ngOnInit(): void {
  }
  public onDate(event: any): void {
    this.medicine.expiryDate=event;
    console.log(this.medicine.expiryDate);
    // this.getData(newDate);
  }
  onSubmit() {
    this.medicineService.addMedicine(this.medicine).subscribe(
      response=>{
        alert("Pharmacy created successfully");
        this.dialogRef.close();
        let currentUrl = this.router.url
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigate([currentUrl]);
      },
      err=>{
    //     console.log(err);
         
    //     // this.hasErrorForNotEmptyName = false;
    //     // this.hasErrorForNotEmptyAddress = false;
    //   //     // this.hasErrorForPositiveBalance = false;
    //   //     // this.hasErrorForNotEmpty=false;
    //   //     // this.hasErrorMsg=false;
    //   //     // this.hasErrorMsgForAccountExisting=false;
    //   if (err.error!= null) {
      
    //   if (err.error.name != null) {
    //       let nameErrorMessage=err.error.name;
    //         if(nameErrorMessage=='Name cannot be empty'){
    //              this.hasErrorForNotEmptyName = true;
    //           }
    //   }
    //   if (err.error.address != null) {
    //     let addressErrorMessage=err.error.address;
    //       if(addressErrorMessage=='Address cannot be empty'){
    //            this.hasErrorForNotEmptyAddress = true;
    //       }
    //  }
       
    // }
  }
      
    
    )

  }


}

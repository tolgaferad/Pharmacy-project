import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Pharmacy } from 'src/models/pharmacyDTO/pharmacy';
import { PharmacyService } from 'src/services/pharmacy.service';

@Component({
  selector: 'app-create-pharm-dialog',
  templateUrl: './create-pharm-dialog.component.html',
  styleUrls: ['./create-pharm-dialog.component.css']
})
export class CreatePharmDialogComponent implements OnInit {
  pharmacy: Pharmacy = {
    name: '',
    address: '',
  }
  hasErrorForNotEmptyName: boolean = false;
  hasErrorForNotEmptyAddress: boolean = false;
  // hasErrorForPositiveBalance: boolean=false;
  // hasErrorForNotEmpty:boolean=false;
  // hasErrorMsg:boolean=false;
  // hasErrorMsgForAccountExisting:boolean=false;
  constructor(public dialog: MatDialog,
              private pharmacyService:PharmacyService,
              private router: Router,
              public dialogRef: MatDialogRef<CreatePharmDialogComponent>) { }

  ngOnInit(): void {

  }

  onSubmit() {
    this.pharmacyService.createPharmacy(this.pharmacy).subscribe(
      response=>{
        alert("Pharmacy created successfully");
        this.dialogRef.close();
        let currentUrl = this.router.url
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigate([currentUrl]);
      },
      err=>{
        console.log(err);
         
        this.hasErrorForNotEmptyName = false;
        this.hasErrorForNotEmptyAddress = false;
      //     // this.hasErrorForPositiveBalance = false;
      //     // this.hasErrorForNotEmpty=false;
      //     // this.hasErrorMsg=false;
      //     // this.hasErrorMsgForAccountExisting=false;
      if (err.error!= null) {
      
      if (err.error.name != null) {
          let nameErrorMessage=err.error.name;
            if(nameErrorMessage=='Name cannot be empty'){
                 this.hasErrorForNotEmptyName = true;
              }
      }
      if (err.error.address != null) {
        let addressErrorMessage=err.error.address;
          if(addressErrorMessage=='Address cannot be empty'){
               this.hasErrorForNotEmptyAddress = true;
          }
     }
       
    }
  }
      
    
    )

  }

}

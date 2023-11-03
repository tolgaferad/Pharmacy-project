import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserOnlyEmail } from 'src/models/userDTO/userOnlyEmail';
import { PharmacyService } from 'src/services/pharmacy.service';

@Component({
  selector: 'app-add-pharmacist-dialog',
  templateUrl: './add-pharmacist-dialog.component.html',
  styleUrls: ['./add-pharmacist-dialog.component.css']
})
export class AddPharmacistDialogComponent implements OnInit {
  userOnlyEmail:UserOnlyEmail={
    email:""
  }
  hasErrorForNotEmptyEmail: boolean = false;
  hasErrorForNotValidEmail: boolean = false;
  hasErrorForNotFoundPharmacist: boolean = false;
  hasErrorForAdminPharmacist:boolean=false;
  hasErrorForPharmacistAlreadyHasPharmacy:boolean=false;
  constructor(public dialog: MatDialog,
              private pharmacyService:PharmacyService,
              private router: Router,
              public dialogRef: MatDialogRef<AddPharmacistDialogComponent>) { }

  ngOnInit(): void {
  }
  onSubmit() {
    this.pharmacyService.addPharmacist(this.userOnlyEmail).subscribe(
      response=>{
        alert("User added successfully");
        this.dialogRef.close();
        let currentUrl = this.router.url
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigate([currentUrl]);
      },
      err=>{
        console.log(err);
         
        this.hasErrorForNotEmptyEmail = false;
        this.hasErrorForNotValidEmail = false;
        this.hasErrorForNotFoundPharmacist = false;
        this.hasErrorForAdminPharmacist = false;
        this.hasErrorForPharmacistAlreadyHasPharmacy=false;
        
      if (err.error!= null) {
      
      if (err.error.email != null) {
          let nameErrorMessage=err.error.email;
            if(nameErrorMessage=='Email is mandatory'){
                 this.hasErrorForNotEmptyEmail = true;
            }
            if(nameErrorMessage=='Invalid email format'){
              this.hasErrorForNotValidEmail=true;
            }
      }
      if(err.error.msg!=null){
        let nameErrorMessage=err.error.msg;
        if(nameErrorMessage=='User with this email not found'){
          this.hasErrorForNotFoundPharmacist=true;
        }
        if(nameErrorMessage=='You cannot add admins of other pharmacies'){
          this.hasErrorForAdminPharmacist=true;
        }
        if(nameErrorMessage=='User already has pharmacy'){
          this.hasErrorForPharmacistAlreadyHasPharmacy=true;
        }
      }

    }
  }
      
    
    )

  }

}

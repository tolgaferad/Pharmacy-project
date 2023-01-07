import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Pharmacy } from 'src/models/pharmacyDTO/pharmacy';
import { PharmacyService } from 'src/services/pharmacy.service';

@Component({
  selector: 'app-edit-pharmacy-dialog',
  templateUrl: './edit-pharmacy-dialog.component.html',
  styleUrls: ['./edit-pharmacy-dialog.component.css']
})
export class EditPharmacyDialogComponent implements OnInit {
  pharmacy: Pharmacy = {
    name: '',
    address: '',
  }
  hasErrorForNotEmptyName: boolean = false;
  hasErrorForNotEmptyAddress: boolean = false;
  constructor(public dialog: MatDialog,
    private pharmacyService:PharmacyService,
    private router: Router,
    public dialogRef: MatDialogRef<EditPharmacyDialogComponent>) { }

  ngOnInit(): void {
  }
  onSubmit() {
    this.pharmacyService.editPharmacy(this.pharmacy).subscribe(
      response=>{
        alert("Pharmacy edited successfully");
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

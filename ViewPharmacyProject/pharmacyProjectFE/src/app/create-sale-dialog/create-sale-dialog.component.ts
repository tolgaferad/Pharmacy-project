import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AddSale } from 'src/models/saleDTO/addSale';
import { SaleService } from 'src/services/sale.service';

@Component({
  selector: 'app-create-sale-dialog',
  templateUrl: './create-sale-dialog.component.html',
  styleUrls: ['./create-sale-dialog.component.css']
})
export class CreateSaleDialogComponent implements OnInit {
  sale:AddSale={
    name:''
 }
  constructor(private saleService:SaleService,
              public dialog: MatDialog,
              private router: Router,
              public dialogRef: MatDialogRef<CreateSaleDialogComponent>) { }

  ngOnInit(): void {
  }
  onSubmit(){
    this.saleService.addSale(this.sale).subscribe(
      response=>{
        alert("Sale created successfully");
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
      })
  }

}

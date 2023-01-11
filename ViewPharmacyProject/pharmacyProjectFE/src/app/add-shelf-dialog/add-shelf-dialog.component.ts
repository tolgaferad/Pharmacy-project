import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AddShelf } from 'src/models/shelfDTO/addShelf';
import { ShelfService } from 'src/services/shelf.service';

@Component({
  selector: 'app-add-shelf-dialog',
  templateUrl: './add-shelf-dialog.component.html',
  styleUrls: ['./add-shelf-dialog.component.css']
})
export class AddShelfDialogComponent implements OnInit {
  shelf:AddShelf={
    name:'',
    capacity:0
}
  constructor(private shelfService:ShelfService,
              public dialog: MatDialog,
              private router: Router,
              public dialogRef: MatDialogRef<AddShelfDialogComponent>) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.shelfService.addShelf(this.shelf).subscribe(
      response=>{
        alert("Shelf created successfully");
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

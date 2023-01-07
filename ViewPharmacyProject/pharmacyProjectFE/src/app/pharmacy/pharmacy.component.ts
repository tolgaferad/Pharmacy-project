import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/models/userDTO/user';
import { UserService } from 'src/services/user.service';
import {MatButtonModule} from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { PharmacyService } from 'src/services/pharmacy.service';
import { MatDialog } from '@angular/material/dialog';
import { CreatePharmDialogComponent } from '../create-pharm-dialog/create-pharm-dialog.component';
import { AddPharmacistDialogComponent } from '../add-pharmacist-dialog/add-pharmacist-dialog.component';
import { EditPharmacyDialogComponent } from '../edit-pharmacy-dialog/edit-pharmacy-dialog.component';

@Component({
  selector: 'app-pharmacy',
  templateUrl: './pharmacy.component.html',
  styleUrls: ['./pharmacy.component.css']
})

export class PharmacyComponent implements OnInit {
  pharmacists:User[]=[];
  selectedId!:number;
  user!:User;
  isDisabled!:boolean;
  selectedUser!:User;
  constructor(private pharmacyService:PharmacyService,
              private userService:UserService,
              private route: ActivatedRoute,
              private router:Router,
              private dialog:MatDialog) { }

  ngOnInit(): void {

    this.getPharmacists();
    this.getUser();
  }
  displayedColumns: string[] = ['name', 'username', 'email', 'createTime'];
  getPharmacists(){
    this.pharmacyService.getAllPharmacists().subscribe(response=>{
      this.pharmacists=response;
    },
    err=>{
        alert("You don't have pharmacy please create Pharmacy")
    }
    )
  }
  onSelect(row:any){
    this.selectedId=row.id;
    console.log(row);
    console.log("Selected item Id: ", this.selectedId)
  }
  deletePharmacist(userId:number){
    this.pharmacyService.deletePharmacistFromPharmacy(userId).subscribe(
      response=>{
          console.log(response);
        let currentUrl = this.router.url
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigate([currentUrl]);
      },
      err =>{

      }
    );
  }
  getUser(){
    this.userService.getUser().subscribe(
      response => {
        if(response.pharmacyId!=0){
          this.isDisabled=true;
        }
      },
      err => {
        console.log(err.error)
      
      }
    )
  }
  addPharmacy(){
    this.dialog.open(CreatePharmDialogComponent);
  }
  addPharmacist(){
    this.dialog.open(AddPharmacistDialogComponent);
  }
  editPharmacy(){
    this.dialog.open(EditPharmacyDialogComponent);
  }
 
}

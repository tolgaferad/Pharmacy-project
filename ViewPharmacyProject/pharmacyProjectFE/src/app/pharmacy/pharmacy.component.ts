import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/models/userDTO/user';
import { UserService } from 'src/services/user.service';
import {MatButtonModule} from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { PharmacyService } from 'src/services/pharmacy.service';

@Component({
  selector: 'app-pharmacy',
  templateUrl: './pharmacy.component.html',
  styleUrls: ['./pharmacy.component.css']
})

export class PharmacyComponent implements OnInit {
  pharmacists:User[]=[];
  selectedId!:number;
  user!:User;
  selectedUser!:User;
  constructor(private pharmacyService:PharmacyService,
              private route: ActivatedRoute,
              private router:Router) { }

  ngOnInit(): void {
    this.getPharmacists();
  }
  displayedColumns: string[] = ['name', 'username', 'email', 'createTime'];
  getPharmacists(){
    this.pharmacyService.getAllPharmacists().subscribe(response=>{
      this.pharmacists=response;
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
 
}

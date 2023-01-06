import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/services/user.service';
import {ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';
import { User } from 'src/models/userDTO/user';
import {MatTableModule} from '@angular/material/table';
import { PharmacyService } from 'src/services/pharmacy.service';
import { Pharmacy } from 'src/models/pharmacyDTO/pharmacy';



@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  routeSub!: Subscription;
  userId!: number;
  selectedId!: number;
  accounts:any=[];
  public selectedName:any;
  private selectedIdIsNull=true;
  isAdmin: boolean = false;
  displayedColumns:string[] =['id','name','balance','accountLimit'];
  user: User = {
      id: 0,
      name: '',
      username: '',
      email: '',
      password: '',
      createTime:'',
      role:'',
    }
  pharmacy:Pharmacy={
    name:'',
    address:''
  }
  constructor(private route: ActivatedRoute,
              private userService:UserService,
              private router:Router,
              private pharmacyService:PharmacyService
              ) { }

  ngOnInit(): void {
    this.routeSub = this.route.params.subscribe(params => {
      // this.userId = params['id'];
    });
    this.getUser();
    this.getPharmacy();

  }
  getUser(){
    this.userService.getUser().subscribe(
      response => {
        this.user.id=response.id;
        this.user.name=response.name;
        console.log(response.role);
        this.checkWhetherIsAdmin(response.role);
        console.log(response);
      },
      err => {
        console.log(err.error)
      
      }
    )
  }
  getPharmacy(){
    this.pharmacyService.getPharmacy().subscribe(
      response => {
        this.pharmacy.name=response.name;
        this.pharmacy.address=response.address;
        console.log(response);
      },
      err => {
        console.log(err.error)
      
      }
    )
  }
  checkWhetherIsAdmin(role:string){
    this.isAdmin=false;
    if(role=='ADMIN'){
      console.log('alo');
      this.isAdmin=true;
    }
  }
  openPharmacyComponent(){
    this.router.navigate(['/pharmacy/']);
  }
  openUserComponent(){
    this.router.navigate(['/user/'])
  }
  openSalesComponent(){
    this.router.navigate(['/sales/'])

  }
  openShelfsComponent(){
    this.router.navigate(['/shelfs/'])

  }
  openMedicinesComponent(){
    this.router.navigate(['/medicines/'])
  }

}

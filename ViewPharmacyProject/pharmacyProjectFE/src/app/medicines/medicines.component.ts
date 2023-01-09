import { AfterViewInit,Component, OnInit, ViewChild } from '@angular/core';
import { ResponseMedicine } from 'src/models/medicineDTO/responseMedicine';
import { MedicineService } from 'src/services/medicine.service';
import {MatTableDataSource} from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
@Component({
  selector: 'app-medicines',
  templateUrl: './medicines.component.html',
  styleUrls: ['./medicines.component.css'],
})
export class MedicinesComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  // medicines!:ResponseMedicine[]=[
  //   {id: 1, name: 'Hydrogen', barcode: '', strength: 'H',manufacturer: '', details: 'Hydrogen', price: '', expiryDate: 'H',shelfName: 'H'},
  //   {id: 1, name: 'Hydrogen', barcode: '', strength: 'H',manufacturer: '', details: 'Hydrogen', price: '', expiryDate: 'H',shelfName: 'H'},
  //   {id: 1, name: 'Hydrogen', barcode: '', strength: 'H',manufacturer: '', details: 'Hydrogen', price: '', expiryDate: 'H',shelfName: 'H'},
  //   {id: 1, name: 'Hydrogen', barcode: '', strength: 'H',manufacturer: '', details: 'Hydrogen', price: '', expiryDate: 'H',shelfName: 'H'},
  //   {id: 1, name: 'Hydrogen', barcode: '', strength: 'H',manufacturer: '', details: 'Hydrogen', price: '', expiryDate: 'H',shelfName: 'H'},
  //   {id: 1, name: 'Hydrogen', barcode: '', strength: 'H',manufacturer: '', details: 'Hydrogen', price: '', expiryDate: 'H',shelfName: 'H'},
  //   {id: 1, name: 'Hydrogen', barcode: '', strength: 'H',manufacturer: '', details: 'Hydrogen', price: '', expiryDate: 'H',shelfName: 'H'},
  //   {id: 1, name: 'Hydrogen', barcode: '', strength: 'H',manufacturer: '', details: 'Hydrogen', price: '', expiryDate: 'H',shelfName: 'H'},
  //   {id: 1, name: 'Hydrogen', barcode: '', strength: 'H',manufacturer: '', details: 'Hydrogen', price: '', expiryDate: 'H',shelfName: 'H'},
  //   {id: 1, name: 'Hydrogen', barcode: '', strength: 'H',manufacturer: '', details: 'Hydrogen', price: '', expiryDate: 'H',shelfName: 'H'},
  //   {id: 1, name: 'Hydrogen', barcode: '', strength: 'H',manufacturer: '', details: 'Hydrogen', price: '', expiryDate: 'H',shelfName: 'H'},
  //   {id: 1, name: 'Hydrogen', barcode: '', strength: 'H',manufacturer: '', details: 'Hydrogen', price: '', expiryDate: 'H',shelfName: 'H'},
  //   {id: 1, name: 'Hydrogen', barcode: '', strength: 'H',manufacturer: '', details: 'Hydrogen', price: '', expiryDate: 'H',shelfName: 'H'},
  //   {id: 1, name: 'Hydrogen', barcode: '', strength: 'H',manufacturer: '', details: 'Hydrogen', price: '', expiryDate: 'H',shelfName: 'H'},
  // ];
  medicines:ResponseMedicine[]=[];
  selectedId!:number;
  dataSource = new MatTableDataSource<ResponseMedicine>();
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  medicine:ResponseMedicine={
    id:0,
    name:'',
    barcode:'',
    strength:'',
    manufacturer:'',
    details:'',
    price:'',
    expiryDate:'',
    shelfName:'',
  }
  displayedColumns: string[] = ['name', 'barcode', 'strength', 'manufacturer','details', 'price', 'expiryDate', 'shelfName'];
  constructor(public medicineService:MedicineService) { }

  ngOnInit(): void {
    this.getMedicines();
  }
  addMedicines(){

  }
  editMedicine(medicineId:number){
    
  }
  addToShelf(){

  }
  deleteMedicine(medicineId:number){

  }
  getMedicines(){
    this.medicineService.getByPharmacy().subscribe(response=>{
      this.dataSource.data=response;
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
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}

import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ResponseMedicine } from 'src/models/medicineDTO/responseMedicine';
import { MedicineService } from 'src/services/medicine.service';

@Component({
  selector: 'app-view-shelf-medicines-dialog',
  templateUrl: './view-shelf-medicines-dialog.component.html',
  styleUrls: ['./view-shelf-medicines-dialog.component.css']
})
export class ViewShelfMedicinesDialogComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  shelfId:number;
  medicines:ResponseMedicine[]=[];
  selectedId!:number;
  shelfs:any=[];
  dataSource = new MatTableDataSource<ResponseMedicine>();
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  constructor(public medicineService:MedicineService,
              private dialog:MatDialog,
              private router:Router,
              @Inject(MAT_DIALOG_DATA) public data: number) { }

  ngOnInit(): void {
    this.getMedicinesForShelf(this.data);
  }
  getMedicinesForShelf(shelfId:number){
    this.medicineService.getByShelf(shelfId).subscribe(response=>{
      this.dataSource.data=response;
    },
    err=>{
        alert("There is not medicines in this shelf")
    }
    )
  }
  displayedColumns: string[] = ['name', 'barcode', 'manufacturer','price','shelfName'];    
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}

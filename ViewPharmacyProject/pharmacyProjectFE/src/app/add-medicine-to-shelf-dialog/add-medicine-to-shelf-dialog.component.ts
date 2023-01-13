import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ResponseMedicine } from 'src/models/medicineDTO/responseMedicine';
import { MedicineService } from 'src/services/medicine.service';
import { ShelfService } from 'src/services/shelf.service';

@Component({
  selector: 'app-add-medicine-to-shelf-dialog',
  templateUrl: './add-medicine-to-shelf-dialog.component.html',
  styleUrls: ['./add-medicine-to-shelf-dialog.component.css']
})
export class AddMedicineToShelfDialogComponent implements OnInit {
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
              private shelfService:ShelfService) { }

  ngOnInit(): void {
    this.getMedicines();
    this.getShelfs();
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
  displayedColumns: string[] = ['name', 'barcode', 'manufacturer','price','shelfName'];
  onSelect(row:any){
    this.selectedId=row.id;
    this.medicineService.addToShelf(this.selectedId,this.shelfId).subscribe(
      response=>{
        this.getMedicines();
      },
      err=>{
        console.log(err);
      }
    )
    console.log(row);
    console.log("Selected item Id: ", this.selectedId)
    }
    
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getShelfs(){
    this.shelfService.getAllShelfs().subscribe(
      response =>{
        this.shelfs=response
        console.log(response);
      },
      err=>{

      }
    )
  }
}

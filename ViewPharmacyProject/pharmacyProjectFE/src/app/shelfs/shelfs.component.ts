import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ResponseShelf } from 'src/models/shelfDTO/responseShelfDTO';
import { MedicineService } from 'src/services/medicine.service';
import { ShelfService } from 'src/services/shelf.service';
import { AddShelfDialogComponent } from '../add-shelf-dialog/add-shelf-dialog.component';

@Component({
  selector: 'app-shelfs',
  templateUrl: './shelfs.component.html',
  styleUrls: ['./shelfs.component.css']
})
export class ShelfsComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  shelfId:number;
  shelfs:ResponseShelf[]=[];
  selectedId!:number;
  dataSource = new MatTableDataSource<ResponseShelf>();
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  constructor(public medicineService:MedicineService,
              private dialog:MatDialog,
              private router:Router,
              private shelfService:ShelfService) { }

  ngOnInit(): void {
    this.getShelfs();
  }
  displayedColumns: string[] = ['id', 'name', 'capacity'];
  onSelect(row:any){
    this.selectedId=row.id;
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
        this.dataSource.data=response
        console.log(response);
      },
      err=>{

      }
    )
  }
  deleteShelf(shelfId:number){
    this.shelfService.delete(shelfId).subscribe(
    response=>{
      console.log(response);
        let currentUrl = this.router.url
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigate([currentUrl]);
    },
    error=>{

    })
  }
  addShelf(){
    this.dialog.open(AddShelfDialogComponent);
  }

}

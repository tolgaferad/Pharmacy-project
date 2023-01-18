import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ResponseSale } from 'src/models/saleDTO/responseSale';
import { SaleService } from 'src/services/sale.service';
import { AddMedicineToSaleDialogComponent } from '../add-medicine-to-sale-dialog/add-medicine-to-sale-dialog.component';
import { CreateSaleDialogComponent } from '../create-sale-dialog/create-sale-dialog.component';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.css']
})
export class SalesComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  sales:ResponseSale[]=[];
  selectedId!:number;
  dataSource = new MatTableDataSource<ResponseSale>();
  displayedColumns: string[] = ['id', 'name', 'price', 'createTime','confirmed'];
  constructor(public saleService:SaleService,
              private dialog:MatDialog,
              private router:Router) { }

  ngOnInit(): void {
    this.getSales();
  }
  createSale(){
    this.dialog.open(CreateSaleDialogComponent);
  }
  addMedicineToSale(){
    this.dialog.open(AddMedicineToSaleDialogComponent,{
      width:'100%',
      height:'90%'
    });
  }
  deleteSale(saleId:number){
    this.saleService.deleteSale(saleId).subscribe(
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
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  onSelect(row:any){
    this.selectedId=row.id;
  }
  getSales(){
    this.saleService.getAllByPharmacy().subscribe(response=>{
      this.dataSource.data=response;
    },
    err=>{

    }
    )
  }

}

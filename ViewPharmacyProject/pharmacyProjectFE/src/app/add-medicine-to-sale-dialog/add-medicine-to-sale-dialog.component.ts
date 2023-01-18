import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ResponseMedicine } from 'src/models/medicineDTO/responseMedicine';
import { ResponseSaleDetail } from 'src/models/saleDetailsDTO/responseSaleDetail';
import { ResponseSale } from 'src/models/saleDTO/responseSale';
import { MedicineService } from 'src/services/medicine.service';
import { SaleService } from 'src/services/sale.service';
import { SnackBarMessageForAddMedToSaleComponent } from '../snack-bar-message-for-add-med-to-sale/snack-bar-message-for-add-med-to-sale.component';


@Component({
  selector: 'app-add-medicine-to-sale-dialog',
  templateUrl: './add-medicine-to-sale-dialog.component.html',
  styleUrls: ['./add-medicine-to-sale-dialog.component.css']
})
export class AddMedicineToSaleDialogComponent implements OnInit {
  @ViewChild('paginator1') paginator1: MatPaginator;
  @ViewChild('paginator2') paginator2:MatPaginator;
  saleId:number;
  medicines:ResponseMedicine[]=[];
  medicinesOfSale:ResponseSaleDetail[]=[];

  selectedId!:number;
  sales:ResponseSale[]=[];
  sale:ResponseSale={
    id:'',
    name:'',
    price:'',
    createTime:'',
    confirmed:false,
    pharmacyName:'',
    saleDetails:[]
  }
  saleMedicinesDataSource=new MatTableDataSource<ResponseSaleDetail>;
  dataSource = new MatTableDataSource<ResponseMedicine>();
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator1;
    this.saleMedicinesDataSource.paginator=this.paginator2;
  }
  constructor(public medicineService:MedicineService,
              private dialog:MatDialog,
              private router:Router,
              private saleService:SaleService,
              public dialogRef: MatDialogRef<AddMedicineToSaleDialogComponent>,
              private _snackBar:MatSnackBar) { }

  ngOnInit(): void {
    this.getMedicines();
    this.getSales();
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
  displayedColumns: string[] = ['name', 'barcode', 'manufacturer','price', 'shelfName'];
  onSelect(row:any){
    this.selectedId=row.id;
    this.saleService.addMedicineToSale(this.selectedId,this.saleId).subscribe(
      response=>{
        this.getMedicines();
        this.getById(this.saleId);
        this._snackBar.openFromComponent(SnackBarMessageForAddMedToSaleComponent, {
          duration: 1 * 1000,
        });
      },
      err=>{
        console.log(err);
      }
    )
    console.log(row);
    console.log("Selected item Id: ", this.selectedId)
    }
    getById(saleId:number){
      this.saleService.getById(this.saleId).subscribe(
        response=>{
          console.log("here")
          this.sale.price=response.price
          this.saleMedicinesDataSource.data=response.saleDetails;
          console.log(this.medicinesOfSale)
          console.log(this.sale.price);
        },
        err=>{

        }
      )
    }
    onSelectSale(){
      this.getById(this.saleId);
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getSales(){
    this.saleService.getAllByPharmacy().pipe().subscribe(
      response=>{
      this.sales=response
      this.sales=this.sales.filter(s=>s.confirmed===false)
    },
    err=>{

    })
  }
  onSubmit(){
    this.saleService.confirmSale(this.saleId).subscribe(
      response=>{
        alert("Sale confirmed successfully");
        this.dialogRef.close();
        let currentUrl = this.router.url
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigate([currentUrl]);
      },
      err=>{
        console.log(err);
        {
          // this.hasErrorForTransactionType=false;
          // this.hasErrorForPositiveAmount=false;
          // this.hasErrorForCategory=false;
          // if(err.error!=null){
          //   if(err.error.type=='Type is mandatory'){
          //     this.hasErrorForTransactionType=true;
          //   }
          //   if(err.error.categoryId=='Category id must be positive'){
          //     this.hasErrorForCategory=true;
          //   }
          //   if(err.error.amount=='Amount must be positive or zero'){
          //     this.hasErrorForPositiveAmount=true;
          //   }
          // }
        }
        // {
        //   this.hasErrorForLimit = false;
        //   this.hasErrorName = false;
        //   this.hasErrorForPositiveBalance = false;
        //   this.hasErrorForNotEmpty=false;
        //   this.hasErrorMsg=false;
        //   this.hasErrorMsgForAccountExisting=false;
        //   if (err.error!= null) {
        //     if(err.error.msg == 'Balance cannot be bigger than account limit'){
        //         this.hasErrorMsg=true;
        //     }
        //     else if(err.error.msg=='Account already exists!'){
        //         this.hasErrorMsgForAccountExisting=true;
        //     }
        //     if (err.error.name != null) {
        //       let nameErrorMessage=err.error.name;
        //       if(nameErrorMessage=='Name cannot be empty'){
        //         this.hasErrorName = true;
        //       }
        //     }
        //     if(err.error.accLimit=='Account limit must be positive'){
        //       this.hasErrorForLimit = true;
        //     }
        //     if (err.error.balance != null) {
        //       let balanceErrorMessage=err.error.balance;
        //       if(balanceErrorMessage=='Balance must be positive or zero'){
        //       this.hasErrorForPositiveBalance = true; 
        //       }
        //       else{
        //         this.hasErrorForNotEmpty=true;
        //       }
        //     }
        // }
      }
    )
  }
}

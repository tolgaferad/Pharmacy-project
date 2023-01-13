import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMedicineToSaleDialogComponent } from './add-medicine-to-sale-dialog.component';

describe('AddMedicineToSaleDialogComponent', () => {
  let component: AddMedicineToSaleDialogComponent;
  let fixture: ComponentFixture<AddMedicineToSaleDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddMedicineToSaleDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddMedicineToSaleDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

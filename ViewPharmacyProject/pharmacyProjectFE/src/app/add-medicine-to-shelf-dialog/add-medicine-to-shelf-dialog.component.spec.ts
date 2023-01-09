import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMedicineToShelfDialogComponent } from './add-medicine-to-shelf-dialog.component';

describe('AddMedicineToShelfDialogComponent', () => {
  let component: AddMedicineToShelfDialogComponent;
  let fixture: ComponentFixture<AddMedicineToShelfDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddMedicineToShelfDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddMedicineToShelfDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

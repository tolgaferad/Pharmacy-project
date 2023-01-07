import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPharmacistDialogComponent } from './add-pharmacist-dialog.component';

describe('AddPharmacistDialogComponent', () => {
  let component: AddPharmacistDialogComponent;
  let fixture: ComponentFixture<AddPharmacistDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPharmacistDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPharmacistDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

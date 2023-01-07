import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPharmacyDialogComponent } from './edit-pharmacy-dialog.component';

describe('EditPharmacyDialogComponent', () => {
  let component: EditPharmacyDialogComponent;
  let fixture: ComponentFixture<EditPharmacyDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditPharmacyDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditPharmacyDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SnackBarMessageForAddMedToSaleComponent } from './snack-bar-message-for-add-med-to-sale.component';

describe('SnackBarMessageForAddMedToSaleComponent', () => {
  let component: SnackBarMessageForAddMedToSaleComponent;
  let fixture: ComponentFixture<SnackBarMessageForAddMedToSaleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SnackBarMessageForAddMedToSaleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SnackBarMessageForAddMedToSaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

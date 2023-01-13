import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSaleDialogComponent } from './create-sale-dialog.component';

describe('CreateSaleDialogComponent', () => {
  let component: CreateSaleDialogComponent;
  let fixture: ComponentFixture<CreateSaleDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateSaleDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateSaleDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

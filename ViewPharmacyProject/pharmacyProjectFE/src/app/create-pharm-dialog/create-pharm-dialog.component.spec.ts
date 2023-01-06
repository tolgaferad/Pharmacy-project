import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePharmDialogComponent } from './create-pharm-dialog.component';

describe('CreatePharmDialogComponent', () => {
  let component: CreatePharmDialogComponent;
  let fixture: ComponentFixture<CreatePharmDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatePharmDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatePharmDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

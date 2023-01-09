import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMedicinesDialogComponent } from './add-medicines-dialog.component';

describe('AddMedicinesDialogComponent', () => {
  let component: AddMedicinesDialogComponent;
  let fixture: ComponentFixture<AddMedicinesDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddMedicinesDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddMedicinesDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

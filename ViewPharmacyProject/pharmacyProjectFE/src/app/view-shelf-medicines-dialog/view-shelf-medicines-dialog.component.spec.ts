import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewShelfMedicinesDialogComponent } from './view-shelf-medicines-dialog.component';

describe('ViewShelfMedicinesDialogComponent', () => {
  let component: ViewShelfMedicinesDialogComponent;
  let fixture: ComponentFixture<ViewShelfMedicinesDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewShelfMedicinesDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewShelfMedicinesDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeTrackerComponent } from './employee-tracker.component';

describe('EmployeeTrackerComponent', () => {
  let component: EmployeeTrackerComponent;
  let fixture: ComponentFixture<EmployeeTrackerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeTrackerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeeTrackerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

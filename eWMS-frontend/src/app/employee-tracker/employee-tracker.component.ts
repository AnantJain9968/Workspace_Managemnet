import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators,ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-employee-tracker',
  imports: [ReactiveFormsModule],
  templateUrl: './employee-tracker.component.html',
  styleUrl: './employee-tracker.component.css'
})
export class EmployeeTrackerComponent implements OnInit {

  employeeForm!: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.employeeForm = this.fb.group({
      employeeName: ['', Validators.required],
      deptName: ['', Validators.required],
      subDeptName: ['', Validators.required],
      operation: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.employeeForm.valid) {
      console.log('Form Submitted', this.employeeForm.value);
      // Handle form submission logic here
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators,ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from '../../environments/env'; 
import { CommonModule } from '@angular/common';

import { ToastrService } from 'ngx-toastr';


import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { catchError, tap, throwError } from 'rxjs';
@Component({
  selector: 'app-employee-tracker',
  standalone: true,
  imports: [ ReactiveFormsModule,MatFormFieldModule,MatInputModule,MatButtonModule,MatOptionModule,CommonModule,MatSelectModule],
  templateUrl: './employee-tracker.component.html',
  styleUrl: './employee-tracker.component.css'
})

export class EmployeeTrackerComponent implements OnInit {

  employeeForm!: FormGroup;
  departments: any[] = [];
  subDepartments: any[] = [];
  jobGrades: string[] = [];

  allSubDepartments = {
    'HR': ['Recruitment', 'Payroll'],
    'Engineering': ['Development', 'QA'],
    'Marketing': ['SEO', 'Content']
  };
  constructor(private fb: FormBuilder,private http: HttpClient,private toastr: ToastrService) { }

  ngOnInit(): void {

    this.getDeptJGData();
    this.employeeForm = this.fb.group({
      employeeName: ['',Validators.required],
      departmentId: ['',Validators.required],
      subDepartmentId: ['',Validators.required],
      jobGradeName: ['',Validators.required]
    });
  }

  getDeptJGData(){
    this.http.get(`${environment.apiUrl}/api/department/getDepartmentAndGrades`).subscribe((data: any) => {
      // this.offices = data;
      // this.filteredOffices = [...this.offices];
      
      
      this.jobGrades = data.jobGrades;
      this.departments = data.departments;
      console.log('Department data:', this.departments);
    });
  }

  onSubmit(): void {
    if (this.employeeForm.valid) {
      console.log('Form Submitted', this.employeeForm.value);
      // Handle form submission logic here
    }
  }

  onDepartmentChange(event: any): void {
    const selectedDepartment = event.value;
    console.log(this.departments);
    const department = this.departments.find(dep => dep.id === selectedDepartment);
    if (department) {
      this.subDepartments = department.subDepartments;
      console.log('Subdepartment value is '+ this.subDepartments);
    } else {
      this.subDepartments = [];
    }
    // Reset the subDepartment control value
    this.employeeForm.get('subDepartmentId')!.setValue('');
  }

  addEmployee(): void {
    if (this.employeeForm.valid) {
      this.http.post(`${environment.apiUrl}/api/seat-allocation/allocateEmployee`, this.employeeForm.value, { responseType: 'text' })
        .pipe(
          tap(response => {
            console.log('Employee added successfully', response);
            this.toastr.success('Success!', 'Employee Added successfully!');
            // Handle success response
          }),
          catchError((error: HttpErrorResponse) => {
            console.error('Error adding employee', error);
            if (error.error instanceof ErrorEvent) {
              // Client-side error
              console.error('Client-side error:', error.error.message);
            } else {
              // Server-side error
              console.error(`Server-side error: ${error.status} - ${error.message}`);
              console.error('Response body:', error.error);
            }
            // Handle error response
            this.toastr.error('Error!', 'Failed to add employee.');
            return throwError(error);
          })
        )
        .subscribe();
    }
  }

  deleteEmployee(): void {
    const employeeId = this.employeeForm.get('employeeName')?.value; // Assuming employeeName is unique and used as ID
    if (employeeId) {
      this.http.delete(`${environment.apiUrl}/api/employee/delete/${employeeId}`).subscribe(response => {
        console.log('Employee deleted successfully', response);
        this.toastr.success('Success!', 'Employee deleted successfully!');
        // Handle success response
      }, error => {
        console.error('Error deleting employee', error);
        // Handle error response
      });
    }
  }

  transferEmployee(): void {
    if (this.employeeForm.valid) {
      this.http.put(`${environment.apiUrl}/api/employee/transfer`, this.employeeForm.value).subscribe(response => {
        console.log('Employee transferred successfully', response);
        // Handle success response
      }, error => {
        console.error('Error transferring employee', error);
        // Handle error response
      });
    }
  }

}

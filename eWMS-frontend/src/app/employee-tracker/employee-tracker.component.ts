import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators,ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/env'; 
import { CommonModule } from '@angular/common';

import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
@Component({
  selector: 'app-employee-tracker',
  standalone: true,
  imports: [ReactiveFormsModule,MatFormFieldModule,MatInputModule,MatButtonModule,MatOptionModule,CommonModule,],
  templateUrl: './employee-tracker.component.html',
  styleUrl: './employee-tracker.component.css'
})
export class EmployeeTrackerComponent implements OnInit {


  employeeForm!: FormGroup;
  departments: string[] = ['HR', 'Engineering', 'Marketing'];
  subDepartments: string[] = [];
  jobGrades: string[] = ['Grade 1', 'Grade 2', 'Grade 3'];

  allSubDepartments = {
    'HR': ['Recruitment', 'Payroll'],
    'Engineering': ['Development', 'QA'],
    'Marketing': ['SEO', 'Content']
  };
  constructor(private fb: FormBuilder,private http: HttpClient) { }

  ngOnInit(): void {

    this.getDeptJGData();
    this.employeeForm = this.fb.group({
      employeeName: ['',Validators.required],
      department: ['',Validators.required],
      subDepartment: ['',Validators.required],
      jobGrade: ['',Validators.required]
    });
  }

  getDeptJGData(){
    this.http.get(`${environment.apiUrl}/api/department/getDepartmentAndGrades`).subscribe((data: any) => {
      // this.offices = data;
      // this.filteredOffices = [...this.offices];
      console.log('Department data:', data);
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
    // Update subDepartments based on the selected department
    if (selectedDepartment === 'HR') {
      this.subDepartments = ['Recruitment', 'Employee Relations'];
    } else if (selectedDepartment === 'Finance') {
      this.subDepartments = ['Accounts Payable', 'Accounts Receivable'];
    } else if (selectedDepartment === 'Engineering') {
      this.subDepartments = ['Software', 'Hardware'];
    } else if (selectedDepartment === 'Sales') {
      this.subDepartments = ['Domestic', 'International'];
    } else {
      this.subDepartments = [];
    }
    // Reset the subDepartment control value
    this.employeeForm.get('subDepartment')!.setValue('');
  }

}

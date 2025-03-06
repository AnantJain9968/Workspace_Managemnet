import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  standalone: true,
  imports: [CommonModule, MatCardModule, MatInputModule, MatFormFieldModule, FormsModule]
})
export class HomeComponent implements OnInit {
  offices = [
    { city: 'New York', country: 'USA', image: 'assets/office.jpg' },
    { city: 'London', country: 'UK', image: 'assets/office.jpg' },
    { city: 'Paris', country: 'France', image: 'assets/office.jpg' },
    { city: 'Berlin', country: 'Germany', image: 'assets/office.jpg' },
    { city: 'Tokyo', country: 'Japan', image: 'assets/office.jpg' },
    { city: 'Sydney', country: 'Australia', image: 'assets/office.jpg' },
    { city: 'Toronto', country: 'Canada', image: 'assets/office.jpg' },
    { city: 'San Francisco', country: 'USA', image: 'assets/office.jpg' },
    { city: 'Singapore', country: 'Singapore', image: 'assets/office.jpg' },
    { city: 'Hong Kong', country: 'China', image: 'assets/office.jpg' },
    { city: 'Dubai', country: 'UAE', image: 'assets/office.jpg' },
    { city: 'Mumbai', country: 'India', image: 'assets/office.jpg' },
    { city: 'São Paulo', country: 'Brazil', image: 'assets/office.jpg' },
    { city: 'Moscow', country: 'Russia', image: 'assets/office.jpg' },
    { city: 'Johannesburg', country: 'South Africa', image: 'assets/office.jpg' },
    { city: 'Shanghai', country: 'China', image: 'assets/office.jpg' },
    { city: 'Mexico City', country: 'Mexico', image: 'assets/office.jpg' },
    { city: 'Buenos Aires', country: 'Argentina', image: 'assets/office.jpg' },
    { city: 'Istanbul', country: 'Turkey', image: 'assets/office.jpg' },
    { city: 'Seoul', country: 'South Korea', image: 'assets/office.jpg' }
  ];

  filteredOffices = [...this.offices];
  filterText = '';
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  navigateToBuilding(site: string): void {
    this.router.navigate(['/building', site]);
  }

  filterOffices(): void {
    this.filteredOffices = this.offices.filter(office =>
      office.city.toLowerCase().includes(this.filterText.toLowerCase())
    );
  }
}

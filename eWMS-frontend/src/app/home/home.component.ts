import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../auth.service';
import { environment } from '../../environments/env'; 

interface Site {
  siteId: number;
  name: string;
  location: string;
  image:string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  standalone: true,
  imports: [CommonModule, MatCardModule, MatInputModule, MatFormFieldModule, FormsModule]
})

export class HomeComponent implements OnInit {

  offices !: Site[];
  authService = inject(AuthService);
  

  // offices = [
  //   { city: 'New York', country: 'USA', image: 'assets/office.jpg' },
  //   { city: 'London', country: 'UK', image: 'assets/office.jpg' },
  //   { city: 'Paris', country: 'France', image: 'assets/office.jpg' },
  //   { city: 'Berlin', country: 'Germany', image: 'assets/office.jpg' },
  //   { city: 'Tokyo', country: 'Japan', image: 'assets/office.jpg' },
  //   { city: 'Sydney', country: 'Australia', image: 'assets/office.jpg' },
  //   { city: 'Toronto', country: 'Canada', image: 'assets/office.jpg' },
  //   { city: 'San Francisco', country: 'USA', image: 'assets/office.jpg' },
  //   { city: 'Singapore', country: 'Singapore', image: 'assets/office.jpg' },
  //   { city: 'Hong Kong', country: 'China', image: 'assets/office.jpg' },
  //   { city: 'Dubai', country: 'UAE', image: 'assets/office.jpg' },
  //   { city: 'Mumbai', country: 'India', image: 'assets/office.jpg' },
  //   { city: 'São Paulo', country: 'Brazil', image: 'assets/office.jpg' },
  //   { city: 'Moscow', country: 'Russia', image: 'assets/office.jpg' },
  //   { city: 'Johannesburg', country: 'South Africa', image: 'assets/office.jpg' },
  //   { city: 'Shanghai', country: 'China', image: 'assets/office.jpg' },
  //   { city: 'Mexico City', country: 'Mexico', image: 'assets/office.jpg' },
  //   { city: 'Buenos Aires', country: 'Argentina', image: 'assets/office.jpg' },
  //   { city: 'Istanbul', country: 'Turkey', image: 'assets/office.jpg' },
  //   { city: 'Seoul', country: 'South Korea', image: 'assets/office.jpg' }
  // ];

  filteredOffices!: Site[];
  filterText = '';
  constructor(private router: Router,private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get(`${environment.apiUrl}/api/sites/siteName`).subscribe((data: any) => {
      this.offices = data;
      this.filteredOffices = [...this.offices];
      console.log('Sites:', this.offices);
    });
    
  }

  navigateToBuilding(siteId: number): void {
    console.log('SiteId from home '+ siteId);
    this.authService.setCurrentSiteId(siteId);
    this.router.navigate(['/building', siteId]);
  }

  filterOffices(): void {
    this.filteredOffices = this.offices.filter(office =>
      office.location.toLowerCase().includes(this.filterText.toLowerCase())
    );
  }
}

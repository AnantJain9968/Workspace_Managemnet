import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { RouterOutlet,RouterModule } from '@angular/router';
import { SiteSidebarComponent } from '../site-sidebar/site-sidebar.component';
import { ViewBuildingComponent } from '../view-building/view-building.component';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-building',
  imports: [CommonModule, MatCardModule,SiteSidebarComponent,ViewBuildingComponent,RouterOutlet,RouterModule],
  standalone: true,
  templateUrl: './building.component.html',
  styleUrl: './building.component.css'
})
export class BuildingComponent implements OnInit {

  siteId: any ;
  buildings = [
    { name: 'Building 1', image: 'assets/building1.jpg' },
    { name: 'Building 2', image: 'assets/building2.jpg' },
    { name: 'Building 3', image: 'assets/building3.jpg' },
    { name: 'Building 4', image: 'assets/building4.jpg' }
  ];

  constructor(private route: ActivatedRoute, private router: Router,private http: HttpClient) { }

  ngOnInit(): void {
    
      this.siteId = this.route.snapshot.paramMap.get('siteId');
    
    // this.siteId = this.route.snapshot.paramMap.get('siteId');
    console.log(this.siteId);

    this.http.get(`http://localhost:8080/api/sites/${this.siteId}`).subscribe((data: any) => {
      // this.offices = data;
      // this.filteredOffices = [...this.offices];
      console.log('Sites:', data);
    });
  }

  navigateToFloor(building: string): void {
    this.router.navigate(['/floor', building]);
  }
}

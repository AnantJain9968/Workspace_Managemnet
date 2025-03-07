import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-view-building',
  imports: [MatCardModule,CommonModule],
  templateUrl: './view-building.component.html',
  styleUrl: './view-building.component.css'
})
export class ViewBuildingComponent implements OnInit{
site: string | null = null;
  buildings = [
    { name: 'Building 1', image: 'assets/building1.jpg' },
    { name: 'Building 2', image: 'assets/building2.jpg' },
    { name: 'Building 3', image: 'assets/building3.jpg' },
    { name: 'Building 4', image: 'assets/building4.jpg' }
  ];

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
     this.site = this.route.snapshot.paramMap.get('siteId');
     console.log("Site ID from View building::", this.site);
  }

  navigateToFloor(building: string): void {
    this.router.navigate(['/floor', building]);
  }
}

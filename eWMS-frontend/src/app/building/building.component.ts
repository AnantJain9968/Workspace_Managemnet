import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { RouterOutlet,RouterModule } from '@angular/router';
import { SiteSidebarComponent } from '../site-sidebar/site-sidebar.component';
import { ViewBuildingComponent } from '../view-building/view-building.component';

@Component({
  selector: 'app-building',
  imports: [CommonModule, MatCardModule,SiteSidebarComponent,ViewBuildingComponent,RouterOutlet,RouterModule],
  standalone: true,
  templateUrl: './building.component.html',
  styleUrl: './building.component.css'
})
export class BuildingComponent implements OnInit {

  site: string | null =null;
  

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.site = this.route.snapshot.paramMap.get('site');
  }

  navigateToFloor(building: string): void {
    this.router.navigate(['/floor', building]);
  }
}

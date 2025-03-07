import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/env';
@Component({
  selector: 'app-view-building',
  imports: [MatCardModule,CommonModule],
  templateUrl: './view-building.component.html',
  styleUrl: './view-building.component.css'
})
export class ViewBuildingComponent implements OnInit{
siteId!: any;
  buildings! : any; 

  constructor(private route: ActivatedRoute, private router: Router,private http: HttpClient) { }

  ngOnInit(): void {
     this.siteId = this.route.snapshot.paramMap.get('siteId');
     console.log("Site ID from View building::", this.siteId);

    
     
         this.http.get(`${environment.apiUrl}/api/sites/${this.siteId}`).subscribe((data: any) => {
           
           console.log('Sites in view building:', data);

           this.buildings=data.buildings;
         });
  }

  navigateToFloor(building: string): void {
    this.router.navigate(['/floor', building]);
  }
}

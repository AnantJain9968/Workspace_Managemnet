import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { environment } from '../../environments/env';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-floor',
  templateUrl: './floor.component.html',
  styleUrls: ['./floor.component.css'],
  standalone: true,
  imports: [CommonModule, MatCardModule]
})
export class FloorComponent implements OnInit {
  buildingId!: any;
  floors! : any;

  constructor(private route: ActivatedRoute,private router: Router,private http: HttpClient) { }

  ngOnInit(): void {
    this.buildingId = this.route.snapshot.paramMap.get('buildingId');

    this.http.get(`${environment.apiUrl}/api/building/${this.buildingId}`).subscribe((data: any) => {
               
               console.log('Floors in  building:', data);
                             this.floors=data.floors;
             });
  }

  navigateToFloorMap(floor: string): void {
    this.router.navigate(['/floor-map', floor]);
  }
  
}

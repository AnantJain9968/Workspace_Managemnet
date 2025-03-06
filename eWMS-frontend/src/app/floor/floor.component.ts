import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-floor',
  templateUrl: './floor.component.html',
  styleUrls: ['./floor.component.css'],
  standalone: true,
  imports: [CommonModule, MatCardModule]
})
export class FloorComponent implements OnInit {
  building: string | null = null;
  floors = [
    { name: 'Floor 1', image: 'assets/floor1.jpg' },
    { name: 'Floor 2', image: 'assets/floor2.jpg' },
    { name: 'Floor 3', image: 'assets/floor3.jpg' }
  ];

  constructor(private route: ActivatedRoute,private router: Router) { }

  ngOnInit(): void {
    this.building = this.route.snapshot.paramMap.get('building');
  }

  navigateToFloorMap(floor: string): void {
    this.router.navigate(['/floor-map', floor]);
  }
  
}

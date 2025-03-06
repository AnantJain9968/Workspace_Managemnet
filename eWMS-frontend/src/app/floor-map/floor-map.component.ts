import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatTooltipModule } from '@angular/material/tooltip';

interface Seat {
  id: string;
  type: string;
  status: string;
}

interface Row {
  id: string;
  seats: Seat[];
}

@Component({
  selector: 'app-floor-map',
  templateUrl: './floor-map.component.html',
  styleUrls: ['./floor-map.component.css'],
  standalone: true,
  imports: [CommonModule, MatTooltipModule]
})
export class FloorMapComponent implements OnInit {
  floor: string | null = null;
  rows: Row[] = [
    {
      id: 'R1',
      seats: [
        { id: 'Seat1', type: 'T4', status: 'Reserved' },
        { id: 'Seat2', type: 'T4', status: 'Reserved' },
        { id: 'Seat3', type: 'T4', status: 'Reserved' },
        { id: 'Seat4', type: 'T4', status: 'Unreserved' },
        { id: 'Seat5', type: 'T2', status: 'Reserved' },
        { id: 'Seat6', type: 'T2', status: 'Reserved' },
        { id: 'Seat7', type: 'T1', status: 'Reserved' },
        { id: 'Seat8', type: 'T0', status: 'Unreserved' }
      ]
    },
    {
      id: 'R2',
      seats: [
        { id: 'Seat1', type: 'T2', status: 'Reserved' },
        { id: 'Seat2', type: 'T2', status: 'Unreserved' }
      ]
    },
    {
      id: 'R3',
      seats: [
        { id: 'Seat1', type: 'T1', status: 'Reserved' }
      ]
    },
    {
      id: 'R4',
      seats: [
        { id: 'Seat1', type: 'T0', status: 'Reserved' }
      ]
    }
  ];

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.floor = this.route.snapshot.paramMap.get('floor');
  }
}

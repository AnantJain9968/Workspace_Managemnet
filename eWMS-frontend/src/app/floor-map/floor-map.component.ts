import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatTooltipModule } from '@angular/material/tooltip';

interface Seat {
  id: string;
  type: string;
  status: string;
}

interface Cubicle{
  cublicleId: string;
  seats: Seat[];
}

interface Row {
  id: string;
  cubicles: Cubicle[];
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
  // rows: Row[] = [
  //   {
  //     id: 'R1',
  //     seats: [
  //       { id: 'Seat1', type: 'T4', status: 'Reserved',cubicle:'01' },
  //       { id: 'Seat2', type: 'T4', status: 'Reserved',cubicle:'01' },
  //       { id: 'Seat3', type: 'T4', status: 'Reserved',cubicle:'01' },
  //       { id: 'Seat4', type: 'T4', status: 'Unreserved',cubicle:'01' },
  //       { id: 'Seat5', type: 'T2', status: 'Reserved',cubicle:'02' },
  //       { id: 'Seat6', type: 'T2', status: 'Reserved' ,cubicle:'02'},
  //       { id: 'Seat7', type: 'T1', status: 'Reserved' ,cubicle:'03'},
  //       { id: 'Seat8', type: 'T0', status: 'Unreserved',cubicle:'04' },
	// 	    { id: 'Seat9', type: 'T2', status: 'Reserved' ,cubicle:'05'},
  //       { id: 'Seat10', type: 'T2', status: 'Reserved' ,cubicle:'05'},
  //       { id: 'Seat11', type: 'T4', status: 'Reserved' ,cubicle:'06'},
  //       { id: 'Seat12', type: 'T4', status: 'Unreserved',cubicle:'06' },
  //       { id: 'Seat13', type: 'T4', status: 'Reserved' ,cubicle:'06'},
  //       { id: 'Seat14', type: 'T4', status: 'Reserved' ,cubicle:'06'},
  //       { id: 'Seat15', type: 'T1', status: 'Reserved' ,cubicle:'07'},
  //       { id: 'Seat16', type: 'T0', status: 'Unreserved',cubicle:'08' }
  //     ]
  //   };

    rows :  Row[] = [{
      id:'R1',
      cubicles:[
        {
          cublicleId:'01',
          seats:[
            {
              id: 'Seat1', type: 'T4', status: 'Reserved'         
            },
            {
              id: 'Seat2', type: 'T4', status: 'Reserved'
            },
            { id: 'Seat3', type: 'T4', status: 'Reserved'},
            { id: 'Seat4', type: 'T4', status: 'Unreserved'}
          ]
        },
        {
          cublicleId:'02',
          seats:[
            {
              id: 'Seat5', type: 'T2', status: 'Reserved'         
            },
            {
              id: 'Seat6', type: 'T2', status: 'Reserved'
            }
          ]
        },
        {
          cublicleId:'03',
          seats:[
            {
              id: 'Seat7', type: 'T1', status: 'Reserved'         
            },
          ]
        },
        {
          cublicleId:'04',
          seats:[
            {
              id: 'Seat8', type: 'T0', status: 'UnReserved'         
            },
          ]
        },
        {
          cublicleId:'05',
          seats:[
            {
              id: 'Seat9', type: 'T4', status: 'Reserved'         
            },
            {
              id: 'Seat10', type: 'T4', status: 'Reserved'
            },
            { id: 'Seat11', type: 'T4', status: 'Reserved'},
            { id: 'Seat12', type: 'T4', status: 'Unreserved'}
          ]
        },
        {
          cublicleId:'06',
          seats:[
            {
              id: 'Seat13', type: 'T2', status: 'Reserved'         
            },
            {
              id: 'Seat14', type: 'T2', status: 'Reserved'
            }
          ]
        },
        {
          cublicleId:'07',
          seats:[
            {
              id: 'Seat15', type: 'T1', status: 'Reserved'         
            },
          ]
        },
        {
          cublicleId:'08',
          seats:[
            {
              id: 'Seat16', type: 'T0', status: 'UnReserved'         
            },
          ]
        }
      ]
    }
  ];
    

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.floor = this.route.snapshot.paramMap.get('floor');
  }
  
}

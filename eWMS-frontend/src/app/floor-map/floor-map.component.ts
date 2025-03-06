import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatTooltipModule } from '@angular/material/tooltip';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { EmployeeDialogComponent } from '../employee-dialog/employee-dialog.component';

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
interface Employee {
  name: string;
  position: string;
  department: string;
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
  private employeeData: { [key: string]: Employee } = {
    'Seat1': { name: 'John Doe', position: 'Software Engineer', department: 'Development' },
    'Seat2': { name: 'Jane Smith', position: 'Project Manager', department: 'Management' },
    'Seat3': { name: 'Alice Johnson', position: 'UX Designer', department: 'Design' },
    // Add more static data as needed
  };
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
              id: 'Seat8', type: 'T0', status: 'Unreserved'         
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
              id: 'Seat15', type: 'T0', status: 'Reserved'         
            },
          ]
        },
        {
          cublicleId:'08',
          seats:[
            {
              id: 'Seat16', type: 'T1', status: 'UnReserved'         
            },
          ]
        }
      ]
    }
  ];
    

  constructor(private route: ActivatedRoute,private http: HttpClient, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.floor = this.route.snapshot.paramMap.get('floor');
  }
  onSeatClick(seatId: string) {
    // this.http.get(`your-api-endpoint/seats/${seatId}`).subscribe((employee: any) => {
    //   this.openDialog(employee);
    // });

    const employee = this.employeeData[seatId];
    if (employee) {
      this.openDialog(employee);
    } else {
      console.error('No employee data found for seat ID:', seatId);
    }
  
}

openDialog(employee: any): void {
  const dialogRef = this.dialog.open(EmployeeDialogComponent, {
    width: '300px',
    data: { 
      name: employee.name,
      position: employee.position,
      department: employee.department
    }
  });

  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');
  });
}
}

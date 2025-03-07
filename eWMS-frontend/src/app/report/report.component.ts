import { Component, OnInit } from '@angular/core';
import { CanvasJSAngularChartsModule } from '@canvasjs/angular-charts';
import { CommonModule } from '@angular/common'; // Import CommonModule
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-report',
  imports: [CanvasJSAngularChartsModule,CommonModule,MatCardModule],
  templateUrl: './report.component.html',
  styleUrl: './report.component.css'
})
export class ReportComponent implements OnInit{
  chartOptionsArray: any[] = [];
  ngOnInit() {
    // Simulated response data
    const responseData = [
      {
        buildingid: "1",
        values: {
          totalSeats: 12,
          vacantSeats: 14
        }
      },
      {
        buildingid: "2",
        values: {
          totalSeats: 12,
          vacantSeats: 14
        }
      },
      {
        buildingid: "3",
        values: {
          totalSeats: 12,
          vacantSeats: 14
        }
      }
    ];

    this.createChartOptions(responseData);
  }

  createChartOptions(data: any[]) {
    this.chartOptionsArray = data.map(building => {
      return {
        animationEnabled: true,
        title: {
          text: `Building ${building.buildingid} - Seats Distribution`
        },
        data: [{
          type: "pie",
          startAngle: -90,
          indexLabel: "{name}: {y}",
          yValueFormatString: "#,###.##'%'",
          dataPoints: [
            { y: building.values.totalSeats, name: "Total Seats" },
            { y: building.values.vacantSeats, name: "Vacant Seats" }
          ]
        }]
      };
    });
  }
}

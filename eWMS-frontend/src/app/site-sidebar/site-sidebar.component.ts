import { Component, inject, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-site-sidebar',
  imports: [RouterModule,CommonModule,MatIconModule,MatListModule,MatCardModule],
  templateUrl: './site-sidebar.component.html',
  styleUrl: './site-sidebar.component.css'
})
export class SiteSidebarComponent implements OnInit {
  selectedTab: string = 'view-buildings';

  
  constructor(private route: ActivatedRoute, private router: Router,private http: HttpClient) { }

  authService = inject(AuthService);


  ngOnInit(): void {}

  selectTab(tab: string) {
    this.selectedTab = tab;
  }

  navigateToBuilding(): void {
    console.log("Hi, I am here");
    console.log("site id in navigation building "+ this.authService.getCurrentSiteId());
    this.router.navigate(['/building', this.authService.getCurrentSiteId()]);
  }
  
  navigateToEmployeeTracker(): void{
    console.log("Hi, I am here", this.authService.getCurrentSiteId());
    this.router.navigate(['/building/employeeTracker', this.authService.getCurrentSiteId()]);
  }

}

import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-site-sidebar',
  imports: [RouterModule,CommonModule,MatIconModule,MatListModule,MatCardModule],
  templateUrl: './site-sidebar.component.html',
  styleUrl: './site-sidebar.component.css'
})
export class SiteSidebarComponent implements OnInit {
  @Input() siteId: any;

  selectedTab: string = 'view-buildings';

  
  constructor(private route: ActivatedRoute, private router: Router,private http: HttpClient) { }


  ngOnInit(): void {
    console.log("sidebar site id 1 is"+this.siteId);
    if (!this.siteId) {
      this.siteId = this.route.snapshot.paramMap.get('siteId');
    }
    
    console.log("sidebar site id 2 is "+this.siteId);
    // this.router.navigate(['/view23/view-building', this.siteId]);
  }

  selectTab(tab: string) {
    this.selectedTab = tab;
  }

}

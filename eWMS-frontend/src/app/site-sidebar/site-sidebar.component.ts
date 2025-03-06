import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-site-sidebar',
  imports: [RouterModule,CommonModule],
  templateUrl: './site-sidebar.component.html',
  styleUrl: './site-sidebar.component.css'
})
export class SiteSidebarComponent {
  @Input() site: string | null =null;

  selectedTab: string = 'view-buildings';

  selectTab(tab: string) {
    this.selectedTab = tab;
  }

}

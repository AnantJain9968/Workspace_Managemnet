import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter, Routes } from '@angular/router';
import { AppComponent } from './app/app.component';
import { LoginComponent } from './app/login/login.component';
import { HomeComponent } from './app/home/home.component';
import { FloorComponent } from './app/floor/floor.component';
import { FloorMapComponent } from './app/floor-map/floor-map.component';
import { EmployeeTrackerComponent } from './app/employee-tracker/employee-tracker.component';
import { BuildingComponent } from './app/building/building.component';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { ViewBuildingComponent } from './app/view-building/view-building.component';
import { ReportComponent } from './app/report/report.component';

import { SiteSidebarComponent } from './app/site-sidebar/site-sidebar.component';





const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  {
    path: 'building', component: SiteSidebarComponent,
    children: [
      { path: ':siteId', component: ViewBuildingComponent },
      // { path: 'reports/:siteId', component: ReportsComponent },
      { path: 'employeeTracker/:siteId', component: EmployeeTrackerComponent },
      // { path: 'team-tracker/:siteId', component: TeamTrackerComponent }
    ]
  },
  { path: 'employeeTracker/:site', component: EmployeeTrackerComponent },
  { path: 'floor/:building', component: FloorComponent },
  { path: 'floor-map/:floor', component: FloorMapComponent },
  //   { path: 'view23',component: BuildingComponent,
  //     children:[{
  //       path:"view-building/:siteId",
  //       component:ViewBuildingComponent
  //     } ] 
  // }
  // { path: 'building', component: BuildingComponent, children: [
  //   { path: 'view-building/:siteId', component: ViewBuildingComponent },
  //   // { path: 'reports/:siteId', component: ReportsComponent },
  //   { path: 'employeeTracker/:siteId', component: EmployeeTrackerComponent },
  //   // { path: 'team-tracker/:siteId', component: TeamTrackerComponent }
  // ]}
];

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    provideHttpClient(withInterceptorsFromDi())
  ]
}).catch(err => console.error(err));



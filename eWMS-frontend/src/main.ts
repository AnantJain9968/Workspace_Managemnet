import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter, Routes } from '@angular/router';
import { AppComponent } from './app/app.component';
import { LoginComponent } from './app/login/login.component';
import { HomeComponent } from './app/home/home.component';
import { BuildingComponent } from './app/building/building.component';
import { FloorComponent } from './app/floor/floor.component';
import { FloorMapComponent } from './app/floor-map/floor-map.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'building/:site', component: BuildingComponent },
  { path: 'floor/:building', component: FloorComponent },
  { path: 'floor-map/:floor', component: FloorMapComponent },
];

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes)
  ]
}).catch(err => console.error(err));

// auth.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public currentSiteId: string | null = null;
  private loggedIn = new BehaviorSubject<boolean>(false);

  getCurrentSiteId() {
    return this.currentSiteId;
  }

  setCurrentSiteId(siteId: any) {
    this.currentSiteId = siteId;
  }

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  login() {
    this.loggedIn.next(true);
  }

  logout() {
    this.loggedIn.next(false);
  }
}
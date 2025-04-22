import { Injectable, NgZone } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ViewportFixService {
  constructor(private ngZone: NgZone) {}

  forceViewportRefresh() {
    this.ngZone.runOutsideAngular(() => {
      window.dispatchEvent(new Event('resize'));
    });
  }

  initFixOnPageShow() {
    this.ngZone.runOutsideAngular(() => {
      window.addEventListener('pageshow', () => {
        this.forceViewportRefresh();
      });
    });
  }
}

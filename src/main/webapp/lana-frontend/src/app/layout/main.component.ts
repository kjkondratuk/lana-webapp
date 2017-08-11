import { Component } from '@angular/core';

@Component({
  selector: 'main-root',
  template: `
    <div class="app-main-content">
        <header-root></header-root>
        <sidebar-root></sidebar-root>
        <router-outlet></router-outlet>
    </div>
  `,
  styles: [`
    .app-main-content {
      width: 90vw;
      height: 100vh;
      display: table;
      margin: 0 auto;
    }
  `]
})
export class MainComponent { }

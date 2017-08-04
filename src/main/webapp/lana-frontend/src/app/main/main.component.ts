import { Component } from '@angular/core';

@Component({
  selector: 'main-root',
  template: `
    <div class="main-content">
        <header-root></header-root>
        <sidebar-root></sidebar-root>
        <content-root></content-root>
    </div>
  `
})
export class MainComponent { }

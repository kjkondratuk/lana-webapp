import { Component } from '@angular/core';

@Component({
  selector: 'header-root',
  template: `
    <div class="app-header-container">
      <h1>Lana Kane</h1>
      <p>The natural counterpart of the archer-discord-bot</p>
    </div>
  `,
  styles: [`
    .app-header-container {
      background-color: rgba(0,0,0,0.8);
    }
    
    h1 {
      padding: .8rem;
      -webkit-margin-before: 0;
      -webkit-margin-after: 0;
    }
    
    p {
      padding-left: .8rem;
      padding-bottom: 1rem;
      -webkit-margin-before: 0;
      -webkit-margin-after: 0;
    }
  `]
})
export class HeaderComponent { }

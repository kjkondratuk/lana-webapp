import { Component } from '@angular/core';

@Component({
  selector: 'content-root',
  template: `
    <h2>Welcome!</h2>
    <p>Select a guild to begin!</p>
  `,
  styles: [`
    :host {
      float: left;
      background-color: rgba(0,0,0,0.6);
      opacity: 10;
      width: calc(100% - 22rem);
      height: calc(91vh - 2rem);
      padding: 1rem;
      -webkit-margin-before: 0;
      -webkit-margin-after: 0;
    }
  `]
})
export class ContentComponent { }

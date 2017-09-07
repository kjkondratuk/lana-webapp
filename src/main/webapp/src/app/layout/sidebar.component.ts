import {Component, OnInit} from '@angular/core';
import {GuildService} from "../guild/guild.service";
import {Guild} from "../guild/guild.model";

@Component({
  selector: 'sidebar-root',
  template: `
    <h3>Guilds</h3>
    <ul>
      <li *ngFor="let guild of guilds"><a [routerLink]="['guild', guild.guildId]">{{ guild.guildName }}</a></li>
    </ul>
  `,
  styles: [`
    :host {
      width: 20rem;
      float: left;
      height: calc(91vh - 1rem);
      background-color: rgba(0,0,0,0.7);
      padding-bottom: 1rem;
    }
    
    h3 {
      text-align: center;
    }
    
    ul {
      list-style-type: none;
      margin: 0;
      -webkit-padding-start: 0;
    }
    
    li {
      text-align: center;
      color: #292929;
      background-color: #a4a4a3;
      margin: .5rem 0rem .5rem 0rem;
      padding: .5rem .5rem .5rem .5rem;
    }
    
    li a {
      text-decoration: none;
      color: #3f3f3f;
    }
    
  `],
  providers: [ GuildService ]
})
export class SidebarComponent implements OnInit {
  private guilds: Guild[];

  constructor(private guildService: GuildService) {  }

  ngOnInit(): void {
    this.guildService.getGuilds()
      .then(guilds => this.guilds = guilds);
  }
}

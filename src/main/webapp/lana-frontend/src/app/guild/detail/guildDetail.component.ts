import {Component, OnDestroy, OnInit} from '@angular/core';
import {GuildService} from "../guild.service";
import {ConfigService} from "../../config/config.service";
import {Config} from "../../config/config.model";
import {ActivatedRoute, Params} from "@angular/router";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'guild-detail-root',
  template: `
    <a routerLink="/"><< Back</a>
    <h2>Configurations</h2>
    <div class="app-guild-details-listing">
      <div class="app-guild-config">
        <div class="guild-config-panel">
          <table>
            <thead>
                <tr>
                  <th>Configuration Name</th>
                  <th>Configuration Data Type</th>
                  <th>Configuration Value</th>
                </tr>
            </thead>
            <tbody>
                <tr *ngFor="let config of guildConfig">
                  <td>{{ config.configType }}</td>
                  <td>{{ config.configDatatype }}</td>
                  <td>{{ config.configValue }}</td>
                </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
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
    
    table thead th {
      padding: 5px;
      font-weight: bolder;
    }
    
    table thead th {
      border-bottom: 2px solid black;
      border-collapse: collapse;
    }
    
    table tbody tr td {
      padding: 5px;
    }
  `],
  providers: [ ConfigService ]
})
export class GuildDetailComponent implements OnInit, OnDestroy{
  private guildConfig: Config[];
  private guildId: string;
  private sub: Subscription;

  constructor(private configService: ConfigService, private route: ActivatedRoute) {  }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      this.guildId = params['guildId'];

      this.configService.getConfigForGuild(this.guildId)
        .then(configs => this.guildConfig = configs);
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}

import { Injectable } from "@angular/core";
import { Guild } from "./guild.model";
import { Http } from "@angular/http";

import "rxjs/add/operator/toPromise";

@Injectable()
export class GuildService {

  private guildUrl: string = "guild/";
  private headers: Headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {  }

  getGuilds(): Promise<Guild[]> {

    return this.http.get(this.guildUrl, this.headers)
      .toPromise()
      .then(response => {
        console.log(response.json());
        return response.json() as Guild[];
      })
      .catch(error => {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
      });
  }
}

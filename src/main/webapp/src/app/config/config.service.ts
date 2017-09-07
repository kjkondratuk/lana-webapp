import { Injectable } from "@angular/core";
import { Config } from "./config.model";
import { Http } from "@angular/http";

import "rxjs/add/operator/toPromise";

@Injectable()
export class ConfigService {

  private configUrl: string = "config/";
  private headers: Headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {  }

  getConfigForGuild(guildId: string): Promise<Config[]> {

    return this.http.get(this.configUrl + 'guild/' + guildId + '/all', this.headers)
      .toPromise()
      .then(response => {
        console.log(response.json());
        return response.json() as Config[];
      })
      .catch(error => {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
      });
  }
}

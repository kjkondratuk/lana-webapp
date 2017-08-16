import "../styles.css"
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from "@angular/router";
import { AppComponent } from './app.component';
import { MainComponent } from "./layout/main.component";
import { HeaderComponent } from "./layout/header.component";
import { SidebarComponent } from "./layout/sidebar.component";
import { ContentComponent } from "./home/content.component";
import { HttpModule } from "@angular/http";
import { GuildDetailComponent } from "./guild/detail/guildDetail.component";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    MainComponent,
    ContentComponent,
    GuildDetailComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot([{
      path: '',
      component: MainComponent,
      children: [
        { path: '', component: ContentComponent },
        { path: 'guild/:guildId', component: GuildDetailComponent }
      ]
    }], {
      enableTracing: false
    })
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }


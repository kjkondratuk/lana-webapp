import "../styles.css"
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from "@angular/router";
import { AppComponent } from './app.component';
import { MainComponent } from "./main/main.component";
import { HeaderComponent } from "./header/header.component";
import { SidebarComponent } from "./sidebar/sidebar.component";
import { ContentComponent } from "./content/content.component";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    MainComponent,
    ContentComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([{
      path: '',
      component: MainComponent
    }])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }


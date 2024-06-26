import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component'; // <-- NgModel lives here
import { FormacionDetailComponent } from './formacion-detail/formacion-detail.component';
import { FormacionComponent } from './formacion/formacion.component';
import { FormacionSearchComponent } from './formacion-search/formacion-search.component';
import { MessagesComponent } from './messages/messages.component';


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    FormacionComponent,
    FormacionDetailComponent,
    MessagesComponent,
    FormacionSearchComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

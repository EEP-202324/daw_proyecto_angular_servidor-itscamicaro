import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component'; // <-- NgModel lives here
import { FormacionDetailComponent } from './formacion-detail/formacion-detail.component';
import { FormacionComponent } from './formacion/formacion.component';
import { MessagesComponent } from './messages/messages.component';


import { AppRoutingModule } from './app-routing.module';


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    FormacionComponent,
    FormacionDetailComponent,
    MessagesComponent
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

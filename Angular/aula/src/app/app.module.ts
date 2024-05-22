import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormacionComponent } from './formacion/formacion.component';
import { FormsModule } from '@angular/forms';
import { FormacionDetailComponent } from './formacion-detail/formacion-detail.component'; // <-- NgModel lives here

@NgModule({
  declarations: [
    AppComponent,
    FormacionComponent,
    FormacionDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

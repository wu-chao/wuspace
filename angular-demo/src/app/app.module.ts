import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {TodoModule} from './todo/todo.module';
import {AppComponent} from './app.component';
import 'hammerjs';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TodoModule
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { UsersItemComponent } from './users/users-item/users-item.component';
import { UsersListComponent } from './users/users-list/users-list.component';
import { LoansListComponent } from './loans/loans-list/loans-list.component';
import { UsersService } from './users/users.service';
import { AlertModule } from 'ng2-bootstrap/ng2-bootstrap';
import { APP_ROUTES } from "./app.routes";
import { RouterModule } from "@angular/router";
import {LoansService} from "./loans/loans.service";

@NgModule({
  declarations: [
    AppComponent,
    UsersItemComponent,
    UsersListComponent,
    LoansListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AlertModule,
    RouterModule.forRoot(APP_ROUTES)
  ],
  providers: [UsersService, LoansService],
  bootstrap: [AppComponent]
})
export class AppModule { }

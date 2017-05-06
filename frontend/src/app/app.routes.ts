import {Routes} from "@angular/router";

import {UsersListComponent} from "./users/users-list/users-list.component";
import {LoansListComponent} from "./loans/loans-list/loans-list.component";

export const APP_ROUTES: Routes = [
    {
        path: 'users',
        component: UsersListComponent
    },
    {
        path: 'loans',
        component: LoansListComponent
    },
    {
        path: '',
        component: UsersListComponent
    },
    {
        path: '**',
        component: UsersListComponent
    }

];

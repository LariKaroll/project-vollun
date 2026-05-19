import { Routes } from '@angular/router';
import { Login } from './components/pages/login/login';
import { UserArea } from './components/pages/user-area/user-area';
import { Register } from './components/pages/register/register';

export const routes: Routes = [
    {path: "login", component: Login },
    {path: "user-area", component: UserArea},
    {path: "register", component: Register}
];

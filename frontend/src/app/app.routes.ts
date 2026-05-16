import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Home } from './components/pages/home/home';
import { UserArea } from './components/pages/user-area/user-area';
import { Register } from './components/pages/register/register';

export const routes: Routes = [
    {path: "login", component: Login },
    {path: "", component: Home},
    {path: "user-area", component: UserArea},
    {path: "register", component: Register}
];

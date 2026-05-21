import { Routes } from '@angular/router';
import { Login } from './components/pages/login/login';
import { UserArea } from './components/pages/user-area/user-area';
import { Register } from './components/pages/register/register';
import { SearchResults } from './components/pages/search-results/search-results';
import { Dashboard } from './components/pages/dashboard/dashboard';
import { UpdatePDF } from './components/pages/update-pdf/update-pdf';

export const routes: Routes = [
    {path: "login", component: Login },
    {path: "user-area", component: UserArea},
    {path: "register", component: Register},
    {path: "search-results", component: SearchResults},
    {path: "dashboard", component: Dashboard},
    {path: "update-pdf", component: UpdatePDF}
];

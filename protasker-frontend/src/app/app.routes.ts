import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { authGuard } from './services/auth.guard';
import { NotificationComponent } from './components/notification/notification.component';
import { ProjectComponent } from './components/project/project.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { VerificationComponent } from './util/verification/verification.component';

export const routes: Routes = [
    { path: '', redirectTo:"home", pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'sign-in', component: SignInComponent },
    { path: 'sign-up', component: SignUpComponent },
    { path: 'notification', component: NotificationComponent },
    { path: 'project', component: ProjectComponent },
    { path: 'user-profile', component: UserProfileComponent },
    { path: 'reset-password', component: VerificationComponent },
    {
        path: 'verify-email',
        loadComponent: () => import('./util/verification/verification.component').then(m=>m.VerificationComponent),
        // canActivate: [authGuard]
    },
    {
        path: 'dashboard',
        loadComponent: () => import('./components/dashboard/dashboard.component').then(m => m.DashboardComponent),
        canActivate: [authGuard]
    },
    // { path: '**', component:  }
];

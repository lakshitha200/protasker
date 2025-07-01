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
import { ProjectCreateComponent } from './components/project/project-create/project-create.component';
import { ProjectDetailsComponent } from './components/project/project-details/project-details.component';
import { WaterfallComponent } from './components/project/waterfall/waterfall.component';
import { AgileComponent } from './components/project/agile/agile.component';
import { ProductBacklogComponent } from './components/project/agile/product-backlog/product-backlog.component';
import { ReportsComponent } from './components/reports/reports.component';
import { TasksComponent } from './components/tasks/tasks.component';

export const routes: Routes = [
    { path: '', redirectTo:"home", pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    {
        path: 'about',
        loadComponent: () => import('./components/about/about.component').then(m=>m.AboutComponent),
    },
    {
        path: 'contact',
        loadComponent: () => import('./components/contact/contact.component').then(m=>m.ContactComponent),
    },
    
    { path: 'sign-in', component: SignInComponent },
    { path: 'sign-up', component: SignUpComponent },
    { path: 'notification', component: NotificationComponent },
    {
        path: 'workspace/workspace-create',
        loadComponent: () => import('./components/project/workspace-create/workspace-create.component').then(m=>m.WorkspaceCreateComponent),
    },
    { 
        path: 'workspace/:wid', 
        component: ProjectComponent,
        children: [
            {
                path: 'project/:pid',
                component: ProjectCreateComponent,
                children: [
                    {
                        path: 'agile/product-backlog',
                        component: ProductBacklogComponent,
                    }
                ]
            },
        ], 
    },
    { 
        // path: 'workspace/:wid/project/:pid', 
         path: 'test', 
        component: ProjectDetailsComponent,
    },
    { 
        // path: 'workspace/:wid/project/:pid', 
         path: 'test1', 
        component: TasksComponent,
    },
      { 
        // path: 'workspace/:wid/project/:pid', 
         path: 'test2', 
        component: WaterfallComponent,
    },
    { 
        // path: 'workspace/:wid/project/:pid', 
         path: 'test3', 
        component: AgileComponent,
    },
    { 
        path: 'project', 
        component: ProjectComponent,
        children: [
            {
                path: 'project-create',
                component: ProjectCreateComponent,
            },
        ], 
    },
        { path: 'verify-email', component: VerificationComponent },
    // {
    //     path: 'verify-email',
    //     loadComponent: () => import('./util/verification/verification.component').then(m=>m.VerificationComponent),
    //     // canActivate: [authGuard]
    // },
    {
        path: 'user-profile',
        loadComponent: () => import('./components/user-profile/user-profile.component').then(m=>m.UserProfileComponent),
        canActivate: [authGuard]
    },
    {
        path: 'reset-password',
        loadComponent: () => import('./util/password-reset/password-reset.component').then(m=>m.PasswordResetComponent),
        // canActivate: [authGuard]
    },
    {
        path: 'dashboard',
        loadComponent: () => import('./components/dashboard/dashboard.component').then(m => m.DashboardComponent),
        // canActivate: [authGuard]
    },
    // { path: '**', component:  }
];

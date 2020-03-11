import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuthGuardService } from './shared/guards/auth.guard';

export const authRoutes: Routes =[
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'register', children: [
      {
        path: '',
        component: RegisterComponent
      },
      {
        path: ':emailId',
        component: RegisterComponent,
        canActivate: [AuthGuardService]
      },
      {
        path: ':add',
        component: RegisterComponent,
        canActivate: [AuthGuardService]
      }
    ] },
    { path: 'login', component: LoginComponent},
    { path: 'dashboard', component: DashboardComponent}];
@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(authRoutes)
  ],
  exports:[
    RouterModule
  ]
})
export class UserRoutingModule { }

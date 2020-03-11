import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { UserRoutingModule } from './user-routing.module';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RegisterModule } from './register/register.module';
import { LoginModule } from './login/login.module';
import { DashboardModule } from './dashboard/dashboard.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LineChartComponent } from './shared/component/line-chart/line-chart.component';
import { UserService } from './user.service';
import { TokenInterceptor } from './shared/helpers/interceptor.service';

@NgModule({
  declarations: [LineChartComponent],
  exports:[UserRoutingModule,LineChartComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    LoginModule,
    DashboardModule,
    RegisterModule,
    UserRoutingModule,
    BrowserModule,
    BrowserAnimationsModule
  ],
  providers: [
    UserService,{
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: []
})
export class UserModule { }

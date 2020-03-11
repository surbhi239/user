import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard.component';
import { DonutChartComponent } from '../shared/component/donut-chart/donut-chart.component';
import { UserThumnailComponent } from '../user-thumnail/user-thumnail.component';

@NgModule({
  declarations: [
  DashboardComponent,DonutChartComponent, UserThumnailComponent],
  exports:[ DashboardComponent,DonutChartComponent,UserThumnailComponent ],
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule
  ],
  providers: [],
  bootstrap: []
})
export class DashboardModule { }

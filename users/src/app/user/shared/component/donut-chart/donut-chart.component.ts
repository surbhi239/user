import { Component, OnInit } from '@angular/core';  
import { Chart } from 'chart.js';  
import { HttpClient } from '@angular/common/http';
import { UserService } from 'src/app/user/user.service';
import { user } from 'src/app/user/User';
@Component({  
  selector: 'app-doughnut',  
  templateUrl: './donut-chart.component.html',  
  styleUrls: ['./donut-chart.component.css']  
})  
export class DonutChartComponent implements OnInit {  
    data: user[];  
    FirstName = [];  
    Age = [];  
    chart = [];  
    constructor(private userService: UserService) { }  
    ngOnInit() {  
      this.userService.getAllUser().subscribe((result: user[]) => {  
        result.forEach(x => {  
          this.FirstName.push(x.firstName);
          this.Age.push(x.age);  
        });  
        this  
        this.chart = new Chart('canvas', {  
          type: 'bar',  
          data: {  
            labels: this.FirstName,  
            datasets: [  
              {  
                data: this.Age,  
                borderColor: '#3cba9f',  
                backgroundColor: [  
                  "#3cb371",  
                  "#0000FF",  
                  "#9966FF",  
                  "#4C4CFF",  
                  "#00FFFF",  
                  "#f990a7",  
                  "#aad2ed",  
                  "#FF00FF",  
                  "Blue",  
                  "Red",  
                  "Blue"  
                ],  
                fill: true  
              }  
            ]  
          },  
          options: {  
            legend: {  
              display: false  
            },  
            scales: {  
              xAxes: [{  
                display: false  
              }],  
              yAxes: [{  
                display: true  
              }],  
            }  
          }  
        });  
      });  
    }  
  }
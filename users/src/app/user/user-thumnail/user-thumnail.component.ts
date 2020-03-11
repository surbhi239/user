import { Component, OnInit, Input, Output } from '@angular/core';
import { user } from '../User';
import { EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-thumnail',
  templateUrl: './user-thumnail.component.html',
  styleUrls: ['./user-thumnail.component.css']
})
export class UserThumnailComponent implements OnInit {
  @Input() user;
  @Output() deleteUsers = new EventEmitter();
  @Output() updateUsers = new EventEmitter();
  constructor() { }

  ngOnInit() {
  }
  deleteFromUsers(){
    this.deleteUsers.emit(this.user);
  }
  updateFromUsers(){
    this.updateUsers.emit(this.user);
  }
}

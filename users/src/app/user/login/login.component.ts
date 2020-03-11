import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { user } from '../User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  constructor(private formBuilder: FormBuilder,private router:Router, private userService: UserService) {
    this.createForm();
   }

  ngOnInit() {
  }

  createForm(){
    this.loginForm = this.formBuilder.group({
      'email': new FormControl(''),
      'password': new FormControl('')
    })
  }
  login()
  {
    this.userService.loginUser(this.loginForm.value).subscribe((res)=>{
      this.router.navigate(['/dashboard']);
    })
  }
}

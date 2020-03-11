import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { UserService } from '../user.service';
import { user } from '../User';
import { Observable } from 'rxjs';
import {map, switchMap} from 'rxjs/operators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  newUser: user;
  getUserDetails: user[];
  getEmailUser: String;
  addUser: String;
  constructor(private formBuilder: FormBuilder,private router:Router, private userService: UserService,private route: ActivatedRoute) { 
    this.createForm();
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.getEmailUser = params['email']
      this.addUser = params['addUser']
    })
    if(this.getEmailUser != undefined || this.getEmailUser != null){
    this.userService.getUser(this.getEmailUser).subscribe((res : user[])=>{
      this.getUserDetails = res;
      this.registerForm.patchValue(this.getUserDetails);
    })
    }
  }

  
  createForm(){
  this.registerForm = this.formBuilder.group({
    'firstName': new FormControl(''), 
    'lastName': new FormControl(''),
    'gender': new FormControl(''),
    'phoneNumber': new FormControl(null),
    'age': new FormControl(null),
    'city': new FormControl(''),
    'country': new FormControl(''),
    'email': new FormControl('', [Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")]),
    'password': new FormControl('',Validators.pattern("")),
    'role': new FormControl('')
  })
}
  register()
  {
    console.log(this.registerForm.value)
    this.userService.registerUser(this.registerForm.value).subscribe((res)=>{
      this.router.navigate(['/login']);
    })
  }
  update(){
    this.userService.updateUser(this.registerForm.value).subscribe((res)=>{
      console.log(res)
      this.router.navigate(['/dashboard']);
    })
  }
  addUserDetails(){
    this.userService.registerUser(this.registerForm.value).subscribe((res)=>{
      this.router.navigate(['/dashboard']);
    })
  }
}


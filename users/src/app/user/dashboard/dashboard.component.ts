import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { user } from '../User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  allUser : user[] = [];
  constructor(private userService: UserService, private router:Router) { 
    this.userService.getAllUser().subscribe((res: user[])=>{
      this.allUser.push(...res)
    });
  }

  ngOnInit() {
    
    console.log(this.allUser)
    
  }

  deleteFromUser(user){
    this.userService.deleteUser(user).subscribe((user) => {
      console.log("user deleted")
    });
    const index = this.allUser.indexOf(user);
      this.allUser.splice(index,1);
  }
  updateFromUsers(user){
    console.log(user.email)
    this.router.navigate(['/register'],{
      queryParams:{email: user.email}
    });
  }
  addUser(){
    this.router.navigate(['/register'],{
      queryParams:{addUser: 'add'}
    });
  }

  
}

import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UserService } from '../../user.service';


@Injectable()
export class AuthGuardService implements CanActivate{

    constructor(private route:Router, private userService: UserService) { }
  
    canActivate(){
      if(!this.userService.ifTokenExpired()){
        console.log('in canActivate');
        return true;
      }
      this.route.navigate(['/login']);
      return false;
    }
  }
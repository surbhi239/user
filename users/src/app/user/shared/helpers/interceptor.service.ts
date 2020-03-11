import { Injectable } from '@angular/core';

import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserService } from '../../user.service';
@Injectable()
export class TokenInterceptor implements HttpInterceptor{

  constructor(private userService:UserService) { }
  
  intercept(request:HttpRequest<any>,next:HttpHandler):Observable<HttpEvent<any>>
  {
    request=request.clone({
      setHeaders:{
        Authorization: `Bearer ${this.userService.getToken()}`
      }
    });
    console.log(request);
    return next.handle(request);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { user } from './User';
import { Observable, BehaviorSubject } from 'rxjs';
import * as jwt_decode from 'jwt-decode';
export const TOKEN_NAME:string = 'jwt_token';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  springEndPoint:string;

  constructor(private http:HttpClient) { 

    this.springEndPoint="http://localhost:9091/user";
   
  }

  registerUser(newUser){
    const url=this.springEndPoint+"/register";
    return this.http.post(url,newUser,{
      responseType:'json'
    });
  }
  loginUser(user){
    const url=this.springEndPoint+"/login";
    return this.http.post(url,user);
  }

  getAllUser(){
    const url= this.springEndPoint+"/allUsers";
    return this.http.get(url);
  }
  deleteUser(user:user){
  const url = `${this.springEndPoint}/deleteUser?emailId=${user.email}`;
  return this.http.delete(url,{responseType:'json'});
}
getUser(emailId){
  const url = `${this.springEndPoint}/getUser?emailId=${emailId}`;
  return this.http.get(url,{responseType:'json'});
}
updateUser(newUser){
  const url=this.springEndPoint+"/updateUser";
  return this.http.post(url,newUser,{
    responseType:'json'
  });
}
setToken(token:string){
  return localStorage.setItem(TOKEN_NAME,token);
}
getToken(){
  return localStorage.getItem(TOKEN_NAME);
}
removeToken(){
  return localStorage.removeItem(TOKEN_NAME);
}
getTokenExpirationDate(token:string): Date {
  const decoded = jwt_decode(token);
  if ( decoded.exp === undefined) return null;
  const date = new Date(0);
  date.setUTCSeconds(decoded.exp);
  return date;
}
ifTokenExpired(token?:string) : boolean{
  if(!token) token = this.getToken();
  if(!token) return true;
  const date = this.getTokenExpirationDate(token);
  if(date === undefined || date === null) return false;
  return !(date.valueOf() > new Date().valueOf());
}
}
import { Injectable } from '@angular/core';
import {HttpClient,HttpResponse,HttpErrorResponse} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http:HttpClient) { }

   get_sigin(){
     const req=this.http.post('http://localhost:8081/api/signin',{
       "username":"user",
       "password":"passs2312"
     }).subscribe( 
       req=>{
          // this part will recive only success response object from server

         console.log(req);  // this print the all of success object in browser console
         console.log(req['address']); // this will print part of the object..
         console.log(req['name']);
         
         // and also u can assign above parametrs into local variable
       },
       err=>{
          // this part will recive only fail response object from server
         console.log(err.error['response']); // this will print part of error object
         
         // console.log(err);  this part print all of the error object
          
          
       }
     );
   }

  test(){
    console.log("this service from userservice");
    
  }

}

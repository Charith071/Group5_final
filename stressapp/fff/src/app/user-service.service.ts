import { Injectable } from '@angular/core';
import {HttpClient,HttpResponse,HttpErrorResponse} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http:HttpClient) { }


  test(){
    console.log("this service from userservice");

  }

}

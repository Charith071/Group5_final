import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse,HttpErrorResponse,HttpHeaders} from '@angular/common/http';
import { Headers,RequestOptions } from '@angular/http';
import { AllUser } from './home/all_user';

@Injectable({
  providedIn: 'root'
})
export class HomeServiceService {

  private baseUrl:string = 'http://localhost:8081/api';
   httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };



  constructor(private http:HttpClient) { }


  get_signin(username:string,password:string){
    return this.http.post(this.baseUrl+'/signin',{username,password});


  }

  get_signup(user:AllUser){
    console.log(JSON.stringify(user));
    return this.http.post(this.baseUrl+'/signup',JSON.stringify(user),this.httpOptions);
  }





}

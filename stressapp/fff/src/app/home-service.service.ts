import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse,HttpErrorResponse} from '@angular/common/http';
import { AllUser }from './home/all_user';

@Injectable({
  providedIn: 'root'
})
export class HomeServiceService {

  private baseUrl:string = 'http://localhost:8081/api';



  constructor(private http:HttpClient) { }


  get_signin(username:string,password:string){
    return this.http.post(this.baseUrl+'/signin',{username,password});


  }

  get_signup(
    username:string,
    password:string,
    address:string,
    birth_date:string,
    gps:string,
    type:string,
    name:string,
    email:string,
  ​  gender:string,
  ​  phone_number:string,
    guadiant_phone_number:string,
    qualification:string,
    certificate:string
  ){
    return this.http.post(this.baseUrl+'/signup',{
      username,
      password,
      address,
      birth_date,
      gps,
      type,
      name,
      email,
    ​  gender,
    ​  phone_number,
      guadiant_phone_number,
      qualification,
      certificate
    });
  }



}

import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse,HttpErrorResponse,HttpHeaders} from '@angular/common/http';
import { AllUser } from './home/all_user';



@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  private baseUrl:string = 'http://localhost:8081/api';
   httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http:HttpClient) { }



  change_user_gps(id:string,gps:string){
    return this.http.post(this.baseUrl+'/user/gps',{id,gps});
  }

  update_stress_level(id:string,level:string){
    return this.http.put(this.baseUrl+'/level',JSON.stringify({id,level}),this.httpOptions);
  }

  get_stress_level(id:string){
    return this.http.post(this.baseUrl+'/getLevel',{id});
  }

  map_counceller(user_id:string,counceller_id:string){
    return this.http.post(this.baseUrl+'/user/mapCounceller',{user_id,counceller_id});
  }

  chat(sender_id:string,receiver_id:string,date_time:string,msg:string){
    return this.http.post(this.baseUrl+'/chat',{sender_id,receiver_id,date_time,msg});
  }

  /*account_setting(user:AllUser){

    //here we must pass an object from the account settings page form fields
    return this.http.post(this.baseUrl+'/accountSetting',JSON.stringify(user));
  }*/

  booking_request(user_id:string,counceller_id:string){
    return this.http.post(this.baseUrl+'/user/Request',{user_id,counceller_id});
  }

  delete_request(user_id:string,request_id:string){
    return this.http.post(this.baseUrl+'/user/deleterequest',{user_id,request_id});
  }

  confirm_response(user_id:string,booking_request_id:string){
    return this.http.post(this.baseUrl+'/user/confirm',{user_id,booking_request_id});
  }

}

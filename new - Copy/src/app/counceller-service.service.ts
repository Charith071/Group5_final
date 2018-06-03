import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse,HttpErrorResponse,HttpHeaders} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class CouncellerServiceService {

  private baseUrl:string = 'http://localhost:8081/api';
   httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http:HttpClient) { }


  change_counceller_gps(id:string,gps:string){
    return this.http.post(this.baseUrl+'/counceller/gps',{id,gps});
  }

  chat(sender_id:string,receiver_id:string,date_time:string,msg:string){
    return this.http.post(this.baseUrl+'/chat',{sender_id,receiver_id,date_time,msg});
  }

  get_patient_details(counceller_id:string,patient_id:string){
    return this.http.post(this.baseUrl+'/PatientDetails',{counceller_id,patient_id});
  }

  addTrack(counceller_id:string,description:string,name:string,max_stress_level:string,min_stress_level:string){
    return this.http.post(this.baseUrl+'/addTrack',{counceller_id,description,max_stress_level,min_stress_level});
  }

  /*account_setting(user:AllUser){

    //here we must pass an object from the account settings page form fields
    return this.http.post(this.baseUrl+'/accountSetting',JSON.stringify(user));
  }*/

  booking_response(counceller_id,request_id,date_time,location,details,payment,counceller_status){
      return this.http.post(this.baseUrl+'/counceller/Response',{counceller_id,request_id,date_time,location,details,payment,counceller_status});
  }

  add_tips(counceller_id:string,tip:string){
    return this.http.post(this.baseUrl+'/counceller/AddTips',{counceller_id,tip})
  }
}

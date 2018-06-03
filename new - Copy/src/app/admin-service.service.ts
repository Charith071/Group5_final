import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse,HttpErrorResponse,HttpHeaders} from '@angular/common/http';
import { URLSearchParams} from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  private baseUrl:string = 'http://localhost:8081/api';
   httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http:HttpClient) { }


  access_control(){
    let search = new URLSearchParams();
    search.set('foo', 'moo');
    //search.set('limit', 25);


  }

  control_track(){

  }

  add_notification(admin_id:string,notice:string,type:string){
    //here admin can add notifications
    return this.http.post(this.baseUrl+'/admin/addNotification',JSON.stringify({admin_id,notice,type}),this.httpOptions);
  }

  control_notice(admin_id:string,notice_id:string,status:string){
    //here admin can control the added notifications where
    return this.http.post(this.baseUrl+'/admin/ControllNotice',JSON.stringify({admin_id,notice_id,status}),this.httpOptions);

  }
}

import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from './../admin-service.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {


  id="1";
  notice="Notice to all users";
  type="user";//this is the type on which the admin should show the notification to

  notice_id="1";
  status="delete";

  msgstatus:string;
  msgerrormsg:string;
  msgcontrolstatus:string;
  msgcontrolerrormsg:string;

  constructor(private adminService:AdminServiceService) { }

  ngOnInit() {

  }


  onSubmitNotice(){

    this.adminService.add_notification(this.id,this.notice,this.type).subscribe(
      (req)=>{
         // this part will recive only success response object from server

        console.log(req);
        this.msgstatus = req['response'];  // this print the all of success object in browser console

        // and also u can assign above parametrs into local variable
      },
      (err)=>{
         // this part will recive only fail response object from server
        this.msgerrormsg = err.error['response']; // this will print part of error object

        // console.log(err);  this part print all of the error object
      }
    );
  }


  onControlNotice(){
    this.adminService.add_notification(this.id,this.notice_id,this.status).subscribe(
      (req)=>{
         // this part will recive only success response object from server

        console.log(req);
        this.msgcontrolstatus = req['response'];  // this print the all of success object in browser console

        // and also u can assign above parametrs into local variable
      },
      (err)=>{
         // this part will recive only fail response object from server
        this.msgcontrolerrormsg = err.error['response']; // this will print part of error object

        // console.log(err);  this part print all of the error object
      }
    );
  }

}

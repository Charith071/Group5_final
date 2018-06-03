import { Component, OnInit } from '@angular/core';
import { UserServiceService} from './../user-service.service';
import { HomeServiceService } from './../home-service.service'
import { AllUser } from '.././home/all_user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user:AllUser;

  //this must be taken from the qusetionaire marks and processing them
  level = "5";
  //this id must be from the user who is currently logged in
  id = "3";

  //this id is from the id selected by the user
  cid="2";

  //this id is from the two councellers selected by the user
  cids="2";
  //this date is the current date
  date='2018-05-30';
  //this  is the message send by the sender;
  message="Hello";

  cidb="2";

  reqid="1";

  booking_request_id="1";


  getlevel:string;
  levelstatus:string;
  status:string;
  msgstatus:string;
  bookstatus:string;
  deletestatus:string;
  confirmstatus:string;

  getlevelerror:string;
  statuserror:string;
  msgerror:string;
  bookerror:string;
  deletestatuserror:string;
  confirmstatuserror:string;
  levelerror:string;

  constructor(private userService:UserServiceService,private homeService:HomeServiceService) { }

  ngOnInit() {

  }


  //processing the questionaire

  onSubmitLevel(){

    this.userService.update_stress_level(this.id,this.level).subscribe(
      (req)=>{
         // this part will recive only success response object from server

        console.log(req);
        this.levelstatus=req['status'];
        // this print the all of success object in browser console

        // and also u can assign above parametrs into local variable
      },
      (err)=>{
         // this part will recive only fail response object from server
        this.levelerror = err.error['response']; // this will print part of error object

        // console.log(err);  this part print all of the error object
      }
    );
  }

  onGetLevel(){
    this.userService.get_stress_level(this.id).subscribe(
      (req)=>{
         // this part will recive only success response object from server

        console.log(req);
        this.getlevel=req['stress_level'];   // this print the all of success object in browser console

        // and also u can assign above parametrs into local variable
      },
      (err)=>{
         // this part will recive only fail response object from server
        this.getlevelerror = err.error['response']; // this will print part of error object

        // console.log(err);  this part print all of the error object
      }
    );
  }



  onMapCounceller(){
    //here we must get the counceller ids selected by map or with the rating
    //the councellers are selected by pressing a button or pressing on the marker on the map

    this.userService.map_counceller(this.id,this.cid).subscribe(
      (req)=>{
         // this part will recive only success response object from server

        console.log(req);
        this.status = req['response']; // this print the all of success object in browser console

        // and also u can assign above parametrs into local variable
      },
      (err)=>{
         // this part will recive only fail response object from server
        this.statuserror = err.error['response']; // this will print part of error object

        // console.log(err);  this part print all of the error object
      }
    );
  }

  onPressSend(){

    //get id of the existing user
    //get one id of the councellers who are selected
    //get the current date of the message
    //get the message
    this.userService.chat(this.id,this.cids,this.date,this.message).subscribe(
      (req)=>{
         // this part will recive only success response object from server

        console.log(req);
        this.msgstatus = req['response'];  // this print the all of success object in browser console

        // and also u can assign above parametrs into local variable
      },
      (err)=>{
         // this part will recive only fail response object from server
        this.msgerror = err.error['response']; // this will print part of error object

        // console.log(err);  this part print all of the error object
      }
    );

  }

  onPressBook(){

    //get the current userid
    //get the id of the selected counceller to book

    this.userService.booking_request(this.id,this.cidb).subscribe(
      (req)=>{
         // this part will recive only success response object from server

        console.log(req);
        this.bookstatus = req['response'];  // this print the all of success object in browser console

        // and also u can assign above parametrs into local variable
      },
      (err)=>{
         // this part will recive only fail response object from server
        this.bookerror = err.error['response']; // this will print part of error object

        // console.log(err);  this part print all of the error object
      }
    );


  }

  onPressDelete(){

    //get the current userid
    //get the id of the request send, this must be saved in alist of requests made by the user,

    this.userService.delete_request(this.id,this.reqid).subscribe(
      (req)=>{
         // this part will recive only success response object from server

        console.log(req);
        this.deletestatus = req['response'];  // this print the all of success object in browser console

        // and also u can assign above parametrs into local variable
      },
      (err)=>{
         // this part will recive only fail response object from server
        this.deletestatuserror=err.error['response']; // this will print part of error object

        // console.log(err);  this part print all of the error object
      }
    );


  }

  onPressConfirm(){

    //get the current userid
    //get the id

    this.userService.delete_request(this.id,this.booking_request_id).subscribe(
      (req)=>{
         // this part will recive only success response object from server

        console.log(req);
        this.confirmstatus = req['response'];  // this print the all of success object in browser console

        // and also u can assign above parametrs into local variable
      },
      (err)=>{
         // this part will recive only fail response object from server
        this.confirmstatuserror= err.error['response']; // this will print part of error object

        // console.log(err);  this part print all of the error object
      }
    );


  }


}

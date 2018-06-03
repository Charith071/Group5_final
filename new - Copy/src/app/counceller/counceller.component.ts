import { Component, OnInit } from '@angular/core';
import {CouncellerServiceService}  from './../counceller-service.service';

@Component({
  selector: 'app-counceller',
  templateUrl: './counceller.component.html',
  styleUrls: ['./counceller.component.css']
})
export class CouncellerComponent implements OnInit {

  id="2";//current counceller id
  uids="3";//userid
  date="2018-07-31";
  message="hello";

  uid="3";//user who is his patient

  description="Calm pop  music";
  name="reveal";
  maxlevel="5";
  minlevel="3";

  reqid="1";
  date_time="2019-1-20";//this must include the date and time on which the meeting is held
  location="private health center galle";
  details="bring a book with you an be on time";
  payment="300";
  councellerstatus="enable";//here get the conceller's status

  tip="drink water";

  msgstatus:string;
  ptdstatus:string;
  trackstatus:string;
  responsestatus:string;
  tipstatus:string;


  msgerrormsg:string;
  trackerrormsg:string;
  detailerrormsg:string;
  responseerrormsg:string;
  tiperrormsg:string;




  constructor(private councellerService:CouncellerServiceService) { }

  ngOnInit() {

  }


  onPressSend(){

    //get id of the existing counceller
    //get one id of the users who are selected
    //get the current date  & time of the message
    //get the message
    this.councellerService.chat(this.id,this.uids,this.date,this.message).subscribe(
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

  onAddTrack(){
    //get the id of the current counceller
    //get the id of one of the patients who receive traetment from him

    //PROBLEM if we add same track with different name

    this.councellerService.addTrack(this.id,this.description,this.name,this.maxlevel,this.minlevel).subscribe(
      (req)=>{
         // this part will recive only success response object from server

        console.log(req);
        this.trackstatus = req['response'];  // this print the all of success object in browser console

        // and also u can assign above parametrs into local variable
      },
      (err)=>{
         // this part will recive only fail response object from server
        this.trackerrormsg = err.error['response']; // this will print part of error object

        // console.log(err);  this part print all of the error object
      }
    );
  }

  onPressPatientDetails(){
    //get the id of the current counceller
    //get the id of one of the patients who receive traetment from him

    this.councellerService.get_patient_details(this.id,this.uid).subscribe(
      (req)=>{
         // this part will recive only success response object from server

        console.log(req);
        this.ptdstatus = req['response'];  // this print the all of success object in browser console

        // and also u can assign above parametrs into local variable
      },
      (err)=>{
         // this part will recive only fail response object from server
        this.detailerrormsg = err.error['response'];// this will print part of error object

        // console.log(err);  this part print all of the error object
      }
    );
  }

  onResponse(){
    //get the id of the current counceller
    //get the id of one of the patients who receive traetment from him

    //here when we add three responses for same request it creates three seperate responses where user will receive three responses

    this.councellerService.booking_response(this.id,this.reqid,this.date,this.location,this.details,this.payment,this.councellerstatus).subscribe(
      (req)=>{
         // this part will recive only success response object from server

        console.log(req);
        this.responsestatus = req['response'];  // this print the all of success object in browser console

        // and also u can assign above parametrs into local variable
      },
      (err)=>{
         // this part will recive only fail response object from server
        this.responseerrormsg = err.error['response']; // this will print part of error object

        // console.log(err);  this part print all of the error object
      }
    );
  }

  onAddTip(){
    //get the id of the current counceller
    //get the id of one of the patients who receive traetment from him

    this.councellerService.add_tips(this.id,this.tip).subscribe(
      (req)=>{
         // this part will recive only success response object from server

        console.log(req);
        this.tipstatus = req['response'];  // this print the all of success object in browser console

        // and also u can assign above parametrs into local variable
      },
      (err)=>{
         // this part will recive only fail response object from server
        this.tiperrormsg = err.error['response']; // this will print part of error object

        // console.log(err);  this part print all of the error object
      }
    );
  }


}

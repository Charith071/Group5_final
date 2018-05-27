import { Component, OnInit } from '@angular/core';
import { HomeServiceService } from './../home-service.service';
import { AllUser } from './all_user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  username:string;
  password:string;
  errorMsg:string;

  address:string;
  birth_date:string;
  gps:string;
  type:string;
  name:string;
  age:number;
  create_date:string;
  email:string
​  gender:string;
​  id:string;
​  phone_number:string;
​  status:string;
  guadiant_phone_number:string;
  qualification:string;
  certificate:string;
  uname:string;
  pass:string;


  constructor(private homeService:HomeServiceService) { }

  ngOnInit() {
  }

  onSignIn(){
    this.homeService.get_signin(this.username,this.password).subscribe(
      (req)=>{
         // this part will recive only success response object from server

        console.log(req);  // this print the all of success object in browser console
        console.log(req['address']); // this will print part of the object..
        console.log(req['name']);

        // and also u can assign above parametrs into local variable
      },
      (err)=>{

         // this part will recive only fail response object from server
        console.log(err.error['response']); // this will print part of error object
        this.errorMsg = err.error['response'];
        // console.log(err);  this part print all of the error object
      }
    );
  }

  onSignUp(){
    this.homeService.get_signup(
      this.uname,
      this.pass,
      this.address,
      this.birth_date,
      this.gps,
      this.type,
      this.name,
      this.email,
    ​  this.gender,
    ​  this.phone_number,
      this.guadiant_phone_number,
      this.qualification,
      this.certificate

    ).subscribe(
      (req)=>{
         // this part will recive only success response object from server

        console.log(req);  // this print the all of success object in browser console

        // and also u can assign above parametrs into local variable
      },
      (err)=>{
         // this part will recive only fail response object from server
        console.log(err.error['response']); // this will print part of error object

        // console.log(err);  this part print all of the error object


      }
    );
  }


}

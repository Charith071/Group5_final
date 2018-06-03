import { Component, OnInit } from '@angular/core';
import { HomeServiceService} from './../home-service.service';
import { AllUser } from './all_user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  uname:string;
  pwd:string;
  signinerrorMsg:string;
  signuperrorMsg:string;
  picture=null;

  user:AllUser = new AllUser();


  constructor(private homeService:HomeServiceService,private router:Router) { }


  ngOnInit() {
    if (window.navigator && window.navigator.geolocation) {
          window.navigator.geolocation.getCurrentPosition(
              position => {
                  //this.geolocationPosition = position,
                  console.log(position.coords.latitude);
                  this.user.latitude = JSON.stringify(position.coords.latitude);
                  //this.user.gps = "34.434220";
                  console.log(position.coords.longitude);
                  this.user.longitude = JSON.stringify(position.coords.longitude);
              },
              error => {
                  switch (error.code) {
                      case 1:
                          console.log('Permission Denied');
                          break;
                      case 2:
                          console.log('Position Unavailable');
                          break;
                      case 3:
                          console.log('Timeout');
                          break;
                  }
              }
          );
      };
  }

  onSelected(event){

  this.user.profile_pic_name= JSON.stringify(event.target.files[0].name);
  //this.user.gps = "34.434220";
}

  onSignIn(){
  this.homeService.get_signin(this.uname,this.pwd).subscribe(
    (req)=>{
       // this part will recive only success response object from server
      console.log(req);  // this print the all of success object in browser console
      console.log(req['address']); // this will print part of the object..
      console.log(req['name']);

      if(req['type']== "user"){
        this.router.navigate(['/user',req['id']]);
      }
      if(req['type'] == "counceller"){
        this.router.navigate(['/counceller',req['id']]);
      }
      if(req['type'] == "admin"){
        this.router.navigate(['/admin'],req['id']);
      }
      // and also u can assign above parametrs into local variable


      //here we get an object as the response. we have to assign these values for future use
      //in other components to an objetct handled by the server
    },
    (err)=>{

       // this part will recive only fail response object from server
      console.log(err.error['response']); // this will print part of error object
      this.signinerrorMsg = err.error['response'];
      // console.log(err);  this part print all of the error object
    }
  );
}

onSignUp(){

  this.homeService.get_signup(this.user).subscribe(
    (req)=>{
       // this part will recive only success response object from server

      console.log(req);  // this print the all of success object in browser console

      // and also u can assign above parametrs into local variable
    },
    (err)=>{
       // this part will recive only fail response object from server
      console.log(err.error['response']);
      this.signuperrorMsg=err.error['response'];// this will print part of error object

      // console.log(err);  this part print all of the error object


    }
  );


}

onUpload(){
  /*  const fd = new FormData();
    fd.append('profile_pic_name',this.picture);
    this.homeService.change_profile_pic(fd).subscribe(
      (res)=>{
        console.log(res);
      },
      (err)=>{
        console.log(err);
      }
    );*/
}

}

import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HomeServiceService {

  constructor() { }

  test(){
    console.log("this is from home service");
    
  }
  
}

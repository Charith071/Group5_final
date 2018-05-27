import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  constructor() { }

  test(){
    console.log("this is from admin service");
    
  }
}

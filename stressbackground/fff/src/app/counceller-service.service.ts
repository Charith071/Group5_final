import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CouncellerServiceService {

  constructor() { }

  test(){
      console.log("This is from counceller service!!");
      
  }
}

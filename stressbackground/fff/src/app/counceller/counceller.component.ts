import { Component, OnInit } from '@angular/core';
import {CouncellerServiceService}  from './../counceller-service.service';

@Component({
  selector: 'app-counceller',
  templateUrl: './counceller.component.html',
  styleUrls: ['./counceller.component.css']
})
export class CouncellerComponent implements OnInit {

  constructor(private councellerService:CouncellerServiceService) { }

  ngOnInit() {
    this.councellerService.test();
  }

}

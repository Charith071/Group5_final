import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CouncellerComponent } from './counceller.component';

describe('CouncellerComponent', () => {
  let component: CouncellerComponent;
  let fixture: ComponentFixture<CouncellerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CouncellerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CouncellerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

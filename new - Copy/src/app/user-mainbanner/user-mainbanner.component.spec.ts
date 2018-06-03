import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserMainbannerComponent } from './user-mainbanner.component';

describe('UserMainbannerComponent', () => {
  let component: UserMainbannerComponent;
  let fixture: ComponentFixture<UserMainbannerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserMainbannerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserMainbannerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCheckstressComponent } from './user-checkstress.component';

describe('UserCheckstressComponent', () => {
  let component: UserCheckstressComponent;
  let fixture: ComponentFixture<UserCheckstressComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserCheckstressComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserCheckstressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

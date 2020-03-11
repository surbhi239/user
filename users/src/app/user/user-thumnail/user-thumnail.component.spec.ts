import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserThumnailComponent } from './user-thumnail.component';

describe('UserThumnailComponent', () => {
  let component: UserThumnailComponent;
  let fixture: ComponentFixture<UserThumnailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserThumnailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserThumnailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

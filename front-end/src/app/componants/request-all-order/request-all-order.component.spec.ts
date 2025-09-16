import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestAllOrderComponent } from './request-all-order.component';

describe('RequestAllOrderComponent', () => {
  let component: RequestAllOrderComponent;
  let fixture: ComponentFixture<RequestAllOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestAllOrderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestAllOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

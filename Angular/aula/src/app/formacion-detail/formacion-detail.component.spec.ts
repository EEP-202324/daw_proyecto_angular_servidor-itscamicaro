import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormacionDetailComponent } from './formacion-detail.component';

describe('FormacionDetailComponent', () => {
  let component: FormacionDetailComponent;
  let fixture: ComponentFixture<FormacionDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FormacionDetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FormacionDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

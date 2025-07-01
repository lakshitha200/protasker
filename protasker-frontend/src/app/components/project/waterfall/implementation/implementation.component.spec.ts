import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImplementationComponent } from './implementation.component';

describe('ImplementationComponent', () => {
  let component: ImplementationComponent;
  let fixture: ComponentFixture<ImplementationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ImplementationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImplementationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

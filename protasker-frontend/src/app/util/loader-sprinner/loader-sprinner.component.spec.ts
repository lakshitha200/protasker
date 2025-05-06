import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoaderSprinnerComponent } from './loader-sprinner.component';

describe('LoaderSprinnerComponent', () => {
  let component: LoaderSprinnerComponent;
  let fixture: ComponentFixture<LoaderSprinnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoaderSprinnerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoaderSprinnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

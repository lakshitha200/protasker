import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SprintBacklogComponent } from './sprint-backlog.component';

describe('SprintBacklogComponent', () => {
  let component: SprintBacklogComponent;
  let fixture: ComponentFixture<SprintBacklogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SprintBacklogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SprintBacklogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

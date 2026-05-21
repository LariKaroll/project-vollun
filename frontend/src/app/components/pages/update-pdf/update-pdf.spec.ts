import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePDF } from './update-pdf';

describe('UpdatePDF', () => {
  let component: UpdatePDF;
  let fixture: ComponentFixture<UpdatePDF>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdatePDF],
    }).compileComponents();

    fixture = TestBed.createComponent(UpdatePDF);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

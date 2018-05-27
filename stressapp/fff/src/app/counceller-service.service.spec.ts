import { TestBed, inject } from '@angular/core/testing';

import { CouncellerServiceService } from './counceller-service.service';

describe('CouncellerServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CouncellerServiceService]
    });
  });

  it('should be created', inject([CouncellerServiceService], (service: CouncellerServiceService) => {
    expect(service).toBeTruthy();
  }));
});

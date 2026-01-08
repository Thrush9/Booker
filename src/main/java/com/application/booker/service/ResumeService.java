package com.application.booker.service;

import com.application.booker.entity.Resume;
import com.application.booker.exception.ApplicantNotFoundException;
import com.application.booker.model.ResumeDTO;

public interface ResumeService {

    Resume addResume(ResumeDTO resumeDTO) throws ApplicantNotFoundException;
}

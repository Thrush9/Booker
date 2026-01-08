package com.application.booker.service;

import com.application.booker.model.ApplicantDTO;
import com.application.booker.projections.ApplicantRecord;

import java.util.List;

public interface ApplicantService {

    ApplicantRecord saveApplicant(ApplicantDTO applicantDTO);

    List<ApplicantRecord> getAllApplicants();
}

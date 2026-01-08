package com.application.booker.serviceImpl;

import com.application.booker.entity.Applicant;
import com.application.booker.entity.Resume;
import com.application.booker.model.ApplicantDTO;
import com.application.booker.projections.ApplicantRecord;
import com.application.booker.repository.ApplicantRepository;
import com.application.booker.repository.ResumeRepository;
import com.application.booker.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public ApplicantRecord saveApplicant(ApplicantDTO applicantDTO) {
       Applicant newApplicant = mapToEntity(applicantDTO);
       Applicant added = applicantRepository.save(newApplicant);
        Resume newResume = new Resume();
        newResume.setApplicant(added);
        newResume.setContent(applicantDTO.getResumeDTO().getContent());
        resumeRepository.save(newResume);
       return mapToRecord(added);
    }

    @Override
    public List<ApplicantRecord> getAllApplicants() {
        return applicantRepository.findAll().stream().map(this::mapToRecord).toList();
    }


    private Applicant mapToEntity(ApplicantDTO applicantDTO) {
        Applicant newApplicant = new Applicant();
        newApplicant.setName(applicantDTO.getName());
        newApplicant.setEmail(applicantDTO.getEmail());
        newApplicant.setMobile(applicantDTO.getMobile());
        newApplicant.setStatus(applicantDTO.getStatus());
        return newApplicant;
    }

    private ApplicantRecord mapToRecord(Applicant applicant) {
        return new ApplicantRecord(applicant.getId(),applicant.getName(),applicant.getEmail(),applicant.getMobile(),applicant.getStatus(),applicant.getResume());
    }
}

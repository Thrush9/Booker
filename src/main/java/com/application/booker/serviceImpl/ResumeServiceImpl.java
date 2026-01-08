package com.application.booker.serviceImpl;

import com.application.booker.entity.Applicant;
import com.application.booker.entity.Resume;
import com.application.booker.exception.ApplicantNotFoundException;
import com.application.booker.model.ResumeDTO;
import com.application.booker.repository.ApplicantRepository;
import com.application.booker.repository.ResumeRepository;
import com.application.booker.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ApplicantRepository applicantRepository;


    @Override
    public Resume addResume(ResumeDTO resumeDTO) throws ApplicantNotFoundException {
        Optional<Applicant> applicantOpt = applicantRepository.findById(resumeDTO.getApplicantId());
        if(applicantOpt.isPresent()){
            Applicant applicant = applicantOpt.get();
            Resume newResume = new Resume();
            newResume.setApplicant(applicant);
            newResume.setContent(resumeDTO.getContent());
            return resumeRepository.save(newResume);
        }else {
            throw new ApplicantNotFoundException("ApplicantId is not Valid");
        }
    }
}

package com.application.booker.controller;

import com.application.booker.model.ApplicantDTO;
import com.application.booker.projections.ApplicantRecord;
import com.application.booker.service.ApplicantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @PostMapping("/addApplicant")
    public ResponseEntity<?> addApplicant(@Valid @RequestBody ApplicantDTO applicantDTO){
        ApplicantRecord applicant = applicantService.saveApplicant(applicantDTO);
        return new ResponseEntity<>(applicant, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllApplicants(){
        List<ApplicantRecord> applicants = applicantService.getAllApplicants();
        return new ResponseEntity<>(applicants, HttpStatus.CREATED);
    }
}

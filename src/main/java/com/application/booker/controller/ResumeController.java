package com.application.booker.controller;


import com.application.booker.entity.Resume;
import com.application.booker.exception.ApplicantNotFoundException;
import com.application.booker.model.ResumeDTO;
import com.application.booker.service.ResumeService;
import com.application.booker.serviceImpl.ResumeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    @Autowired
    private ResumeServiceImpl resumeService;

    @PostMapping("/addResume")
    public ResponseEntity<?> addResume(@Valid @RequestBody ResumeDTO resumeDTO) throws ApplicantNotFoundException {
        Resume resume = resumeService.addResume(resumeDTO);
        return new ResponseEntity<>(resume, HttpStatus.CREATED);
    }
}

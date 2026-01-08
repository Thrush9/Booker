package com.application.booker.projections;

import com.application.booker.entity.Resume;

public record ApplicantRecord (Integer id, String name, String email, String mobile, Boolean status, Resume resume ){
}

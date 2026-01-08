package com.application.booker.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDTO {

    @NotBlank(message = "Name cannot be Empty")
    private String name;
    @NotBlank(message = "Email cannot be Empty")
    @Email
    private String email;
    @NotBlank(message = "mobile cannot be Empty")
    @Min(message = "Mobile should be alteast 5 characters", value = 5)
    private String mobile;

    private Boolean status;

    private ResumeDTO resumeDTO;
}

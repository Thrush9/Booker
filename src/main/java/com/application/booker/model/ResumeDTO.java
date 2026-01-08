package com.application.booker.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDTO {

    @NotBlank(message = "Content cannot be Empty")
    private String content;

    @NotNull
    private Integer applicantId;
}

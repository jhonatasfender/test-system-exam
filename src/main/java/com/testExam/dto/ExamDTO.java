package com.testExam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExamDTO {
    private Long id;

    @NotBlank
    private String patientName;

    @NotBlank
    private String patientAge;

    @NotNull
    private Integer patientGender;

    @NotBlank
    private String physicianName;

    @NotBlank
    private String physicianCRM;

    @NotBlank
    private String procedureName;

}

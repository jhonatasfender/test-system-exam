package com.testExam.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exam {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String patientName;

    @NotNull
    private Integer patientAge;

    @NotBlank
    private String patientGender;

    @NotBlank
    private String physicianName;

    @NotBlank
    private String physicianCRM;

    @NotBlank
    private String procedureName;

    private Boolean collectPixeonCoins = false;

    @ManyToOne
    @NotNull
    private HealthcareInstitution healthcareInstitution;

}

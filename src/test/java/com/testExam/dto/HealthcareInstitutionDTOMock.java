package com.testExam.dto;

import com.testExam.entity.HealthcareInstitution;

public class HealthcareInstitutionDTOMock {
    public static HealthcareInstitutionDTO get() {
        return new HealthcareInstitutionDTO(
            1L,
            "72.899.442/0001-22", "Ana e Luiza Gráfica ME"
        );
    }
}

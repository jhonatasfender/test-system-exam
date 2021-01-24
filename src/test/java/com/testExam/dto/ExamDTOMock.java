package com.testExam.dto;

public class ExamDTOMock {

    public static ExamDTO get() {
        return new ExamDTO(
            Long.valueOf(1), "Débora Carolina Monteiro", 20, "Feminino",
            "DANILO BOAVENTURA JOSÉ DE OLIVEIRA", "149639",
            "Creatinofosfoquinase MB", 1L
        );
    }

    public static ExamDTO get(HealthcareInstitutionDTO healthcareInstitutionDTO) {
        return new ExamDTO(
            Long.valueOf(1), "Débora Carolina Monteiro", 20, "Feminino",
            "DANILO BOAVENTURA JOSÉ DE OLIVEIRA", "149639",
            "Creatinofosfoquinase MB", healthcareInstitutionDTO.getId()
        );
    }

    public static ExamUpdateDTO update(Long id) {
        return new ExamUpdateDTO(
            id, "Débora Carolina Monteiro", 20, "Feminino",
            "DANILO BOAVENTURA JOSÉ DE OLIVEIRA", "149639",
            "Creatinofosfoquinase MB"
        );
    }

}

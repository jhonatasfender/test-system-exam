package com.testExam.entity;

public class ExamMock {

    public static Exam get() {
        return new Exam(
            null, "Débora Carolina Monteiro", 20, "Feminino",
            "DANILO BOAVENTURA JOSÉ DE OLIVEIRA", "149639",
            "Creatinofosfoquinase MB", false,
            HealthcareInstitutionMock.get()
        );
    }

    public static Exam get(HealthcareInstitution healthcareInstitutionMock) {
        return new Exam(
            null, "Débora Carolina Monteiro", 20, "Feminino",
            "DANILO BOAVENTURA JOSÉ DE OLIVEIRA", "149639",
            "Creatinofosfoquinase MB", false,
            healthcareInstitutionMock
        );
    }

}

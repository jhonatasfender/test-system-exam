package com.testExam.repository;

import com.testExam.entity.HealthcareInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HealthcareInstitutionRepository extends JpaRepository<HealthcareInstitution, Long> {
    Optional<HealthcareInstitution> findByCNPJ(String cnpj);
}

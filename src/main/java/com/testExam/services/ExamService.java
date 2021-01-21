package com.testExam.services;

import com.testExam.dto.ExamDTO;
import com.testExam.entity.Exam;
import com.testExam.entity.HealthcareInstitution;
import com.testExam.repository.ExamRepository;
import com.testExam.repository.HealthcareInstitutionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ExamService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private HealthcareInstitutionRepository healthcareInstitutionRepository;

    @Transactional
    public ExamDTO save(ExamDTO exam) {
        Optional<HealthcareInstitution> institution = healthcareInstitutionRepository.findById(exam.getHealthcareInstitutionId());

        if (!institution.isEmpty()) {
            throw new IllegalArgumentException("Não foi possível localizar esse registro");
        }

        institution.get().setPixeonCoins(institution.get().getPixeonCoins() - 1);

        Exam map = modelMapper.map(exam, Exam.class);
        map.setHealthcareInstitution(institution.get());

        Exam saved = examRepository.save(map);

        return modelMapper.map(saved, ExamDTO.class);
    }

    public ExamDTO findByID(Long id) {
        Optional<Exam> find = examRepository.findById(id);

        if (!find.isEmpty()) {
            throw new IllegalArgumentException("Não foi possível localizar esse registro");
        }

        return modelMapper.map(find.get(), ExamDTO.class);
    }
}

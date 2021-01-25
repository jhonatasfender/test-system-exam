package com.testExam.services;

import com.testExam.dto.ExamDTO;
import com.testExam.dto.ExamUpdateDTO;
import com.testExam.entity.Exam;
import com.testExam.entity.HealthcareInstitution;
import com.testExam.exception.CoinsException;
import com.testExam.exception.NotFoundException;
import com.testExam.exception.PermissionException;
import com.testExam.repository.ExamRepository;
import com.testExam.repository.HealthcareInstitutionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.testExam.exception.Message.*;

@Service
@Transactional(readOnly = true)
public class ExamService {

    private final Integer AMOUNT_TO_BE_CHARGED = 1;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private HealthcareInstitutionRepository healthcareInstitutionRepository;

    @Transactional
    public ExamDTO save(ExamDTO exam) {
        HealthcareInstitution institution = healthcareInstitutionRepository.findById(exam.getHealthcareInstitutionId())
            .orElseThrow(() -> new IllegalArgumentException("Não foi possível localizar esse registro!"));

        validPixeonCoins(institution);
        institution.setPixeonCoins(institution.getPixeonCoins() - AMOUNT_TO_BE_CHARGED);

        Exam map = modelMapper.map(exam, Exam.class);
        map.setHealthcareInstitution(institution);

        Exam saved = examRepository.save(map);

        return modelMapper.map(saved, ExamDTO.class);
    }

    @Transactional
    public ExamDTO update(ExamUpdateDTO exam, Long healthcareInstitutionId) {
        Exam map = modelMapper.map(exam, Exam.class);

        Exam find = examRepository.findById(map.getId())
            .orElseThrow(() -> new NotFoundException(REGISTRATION_NOT_LOCATED));
        permission(find, healthcareInstitutionId);
        map.setHealthcareInstitution(find.getHealthcareInstitution());

        Exam saved = examRepository.save(map);
        return modelMapper.map(saved, ExamDTO.class);
    }

    @Transactional
    public ExamDTO findByID(Long id, Long healthcareInstitutionId) {
        Exam find = examRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(REGISTRATION_NOT_LOCATED));

        permission(find, healthcareInstitutionId);
        validPixeonCoins(find.getHealthcareInstitution());
        collectPixeonCoins(find);

        return modelMapper.map(find, ExamDTO.class);
    }

    private void collectPixeonCoins(Exam exam) {
        if (!exam.getCollectPixeonCoins()) {
            HealthcareInstitution health = exam.getHealthcareInstitution();
            health.setPixeonCoins(health.getPixeonCoins() - 1);
            healthcareInstitutionRepository.save(health);

            exam.setCollectPixeonCoins(true);
            examRepository.save(exam);
        }
    }

    private void permission(Exam exam, Long healthcareInstitutionId) {
        if (!exam.getHealthcareInstitution().getId().equals(healthcareInstitutionId)) {
            throw new PermissionException(NO_PERMISSION_FOR_THIS_EXAM);
        }
    }

    private void validPixeonCoins(HealthcareInstitution institution) {
        if (institution.getPixeonCoins() <= 0) {
            throw new CoinsException(BUY_MORE_COINS);
        }
    }

    @Transactional
    public void delete(Long id, Long healthcareInstitutionId) {
        Exam find = examRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(REGISTRATION_NOT_LOCATED));

        permission(find, healthcareInstitutionId);
        examRepository.delete(find);
    }
}

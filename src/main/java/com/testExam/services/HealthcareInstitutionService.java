package com.testExam.services;

import com.testExam.dto.HealthcareInstitutionDTO;
import com.testExam.dto.HealthcareInstitutionViewDTO;
import com.testExam.entity.HealthcareInstitution;
import com.testExam.repository.HealthcareInstitutionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class HealthcareInstitutionService {

    private final Integer PIXEON_COINS = 20;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HealthcareInstitutionRepository healthcareInstitutionRepository;

    @Transactional
    public HealthcareInstitutionViewDTO save(HealthcareInstitutionDTO dto) {
        HealthcareInstitution map = modelMapper.map(dto, HealthcareInstitution.class);

        Optional<HealthcareInstitution> exist = healthcareInstitutionRepository.findByCNPJ(dto.getCNPJ());
        if (exist.isPresent()) {
            throw new RuntimeException("Nesse CNPJ já existe uma instituição cadastrada.");
        }

        map.setPixeonCoins(PIXEON_COINS);

        HealthcareInstitution saved = healthcareInstitutionRepository.save(map);
        return modelMapper.map(saved, HealthcareInstitutionViewDTO.class);
    }


    public HealthcareInstitutionViewDTO findByID(Long id) {
        HealthcareInstitution find = healthcareInstitutionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Não foi possível localizar esse registro"));

        return modelMapper.map(find, HealthcareInstitutionViewDTO.class);
    }
}

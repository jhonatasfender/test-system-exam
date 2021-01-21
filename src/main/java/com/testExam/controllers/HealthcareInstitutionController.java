package com.testExam.controllers;

import com.testExam.dto.ExamDTO;
import com.testExam.dto.HealthcareInstitutionDTO;
import com.testExam.dto.HealthcareInstitutionViewDTO;
import com.testExam.services.HealthcareInstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;

@RestController
@RequestMapping("healthcare-institution")
public class HealthcareInstitutionController {

    @Autowired
    private HealthcareInstitutionService healthcareInstitutionService;

    @PostMapping
    public ResponseEntity<HealthcareInstitutionViewDTO> save(
        @Valid @RequestBody HealthcareInstitutionDTO institutionDTO
    ) {
        HealthcareInstitutionViewDTO dto = healthcareInstitutionService.save(institutionDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(location).body(dto);
    }

    @GetMapping("{id}")
    public ResponseEntity<HealthcareInstitutionViewDTO> findById(@PathParam("id") Long id) {
        HealthcareInstitutionViewDTO dto = healthcareInstitutionService.findByID(id);
        return ResponseEntity.ok(dto);
    }
}

package com.testExam.controllers;

import com.testExam.dto.ExamDTO;
import com.testExam.dto.ExamUpdateDTO;
import com.testExam.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping
    public ResponseEntity<ExamDTO> save(@Valid @RequestBody ExamDTO exam) {
        ExamDTO dto = examService.save(exam);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(location).body(dto);
    }

    @GetMapping("{id}/{healthcareInstitutionId}")
    public ResponseEntity<ExamDTO> findById(
        @PathVariable("id") Long id,
        @PathVariable("healthcareInstitutionId") Long healthcareInstitutionId
    ) {
        ExamDTO dto = examService.findByID(id, healthcareInstitutionId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("{healthcareInstitutionId}")
    public ResponseEntity<ExamDTO> update(
        @PathVariable("healthcareInstitutionId") Long healthcareInstitutionId,
        @Valid @RequestBody ExamUpdateDTO exam
    ) {
        ExamDTO dto = examService.update(exam, healthcareInstitutionId);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}/{healthcareInstitutionId}")
    public ResponseEntity delete(
        @PathVariable("id") Long id,
        @PathVariable("healthcareInstitutionId") Long healthcareInstitutionId
    ) {
        examService.delete(id, healthcareInstitutionId);
        return ResponseEntity.ok().build();
    }
}

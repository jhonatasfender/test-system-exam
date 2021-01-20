package com.testExam.controllers;

import com.testExam.dto.ExamDTO;
import com.testExam.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController("exam")
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
}

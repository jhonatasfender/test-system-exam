package com.testExam.services;

import com.testExam.dto.ExamDTO;
import com.testExam.entity.Exam;
import com.testExam.repository.ExamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ExamService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExamRepository examRepository;

    public ExamDTO save(ExamDTO exam) {
        Exam saved = examRepository.save(modelMapper.map(exam, Exam.class));
        return modelMapper.map(saved, ExamDTO.class);
    }
}

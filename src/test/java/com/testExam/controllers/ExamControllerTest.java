package com.testExam.controllers;

import com.testExam.dto.ExamDTO;
import com.testExam.dto.ExamDTOMock;
import com.testExam.dto.ExamUpdateDTO;
import com.testExam.dto.HealthcareInstitutionDTO;
import com.testExam.entity.Exam;
import com.testExam.entity.ExamMock;
import com.testExam.entity.HealthcareInstitution;
import com.testExam.entity.HealthcareInstitutionMock;
import com.testExam.repository.ExamRepository;
import com.testExam.repository.HealthcareInstitutionRepository;
import com.testExam.util.TestingUtilities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ExamControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HealthcareInstitutionRepository healthcareInstitutionRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ModelMapper modelMapper;

    @AfterEach
    public void down() {
        examRepository.deleteAll();
        healthcareInstitutionRepository.deleteAll();
    }

    @Test
    public void testNullFields() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/exam")
            .content("{}")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andReturn();
    }

    @Test
    public void testValidIfExamNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/exam/234523/1")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.errorMessage")
                    .value("Não foi possível localizar esse registro!")
            )
            .andReturn();
    }

    @Test
    public void testSave() throws Exception {
        HealthcareInstitution health = healthcareInstitutionRepository.save(HealthcareInstitutionMock.get());
        ExamDTO exam = ExamDTOMock.get(modelMapper.map(health, HealthcareInstitutionDTO.class));
        mockMvc.perform(MockMvcRequestBuilders.post("/exam")
            .content(TestingUtilities.json(exam))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
            .andReturn();
    }

    @Test
    public void testGet() throws Exception {
        HealthcareInstitution healthcare = healthcareInstitutionRepository.save(HealthcareInstitutionMock.get());
        Exam exam = examRepository.save(ExamMock.get(healthcare));

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/" + exam.getId() + "/" + healthcare.getId())
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.patientName").value(exam.getPatientName()))
            .andReturn();
    }

    @Test
    public void testUpdate() throws Exception {
        HealthcareInstitution healthcare = healthcareInstitutionRepository.save(HealthcareInstitutionMock.get());
        Exam exam = examRepository.save(ExamMock.get(healthcare));
        Exam examSecond = examRepository.save(ExamMock.get(healthcare));

        ExamUpdateDTO examDTO = modelMapper.map(exam, ExamUpdateDTO.class);
        examDTO.setPatientName("Louise Maya Duarte");

        mockMvc.perform(MockMvcRequestBuilders.put("/exam/" + healthcare.getId())
            .content(TestingUtilities.json(examDTO))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        mockMvc.perform(MockMvcRequestBuilders.put("/exam/" + healthcare.getId())
            .content(TestingUtilities.json(ExamDTOMock.update(examSecond.getId())))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        examDTO.setId(Long.valueOf(12342));
        mockMvc.perform(MockMvcRequestBuilders.put("/exam/" + healthcare.getId())
            .content(TestingUtilities.json(examDTO))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.errorMessage")
                    .value("Não foi possível localizar esse registro!")
            )
            .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/" + exam.getId() + "/" + healthcare.getId())
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.patientName").value("Louise Maya Duarte"))
            .andReturn();
    }

    @Test
    public void testPermission() throws Exception {
        HealthcareInstitution healthcare = healthcareInstitutionRepository.save(HealthcareInstitutionMock.get());
        Exam exam = examRepository.save(ExamMock.get(healthcare));

        HealthcareInstitution healthcareSecond = healthcareInstitutionRepository.save(HealthcareInstitutionMock.get());

        ExamUpdateDTO examDTO = modelMapper.map(exam, ExamUpdateDTO.class);

        mockMvc.perform(MockMvcRequestBuilders.put("/exam/" + healthcareSecond.getId())
            .content(TestingUtilities.json(examDTO))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.errorMessage")
                    .value("Sem permissão para esse exame!")
            )
            .andReturn();

        HealthcareInstitution healthcareThird = healthcareInstitutionRepository.save(HealthcareInstitutionMock.get());
        Exam examSecond = examRepository.save(ExamMock.get(healthcareThird));

        mockMvc.perform(MockMvcRequestBuilders.delete("/exam/" + exam.getId() + "/" + healthcareThird.getId())
            .content(TestingUtilities.json(examSecond))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.errorMessage")
                    .value("Sem permissão para esse exame!")
            )
            .andReturn();
    }

    @Test
    public void testWithoutCoins() throws Exception {
        HealthcareInstitution healthcare = healthcareInstitutionRepository.save(HealthcareInstitutionMock.get());
        Exam exam = examRepository.save(ExamMock.get(healthcare));
        ExamDTO dto = modelMapper.map(exam, ExamDTO.class);
        dto.setId(null);
        for (Integer i = 20; i >= 1; i--) {
            mockMvc.perform(MockMvcRequestBuilders.post("/exam")
                .content(TestingUtilities.json(dto))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andReturn();
        }

        mockMvc.perform(MockMvcRequestBuilders.post("/exam")
            .content(TestingUtilities.json(dto))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.errorMessage")
                    .value("Por favor adquirir mais moedas!")
            )
            .andReturn();
    }

    @Test
    public void deleteExamById() throws Exception {
        HealthcareInstitution healthcare = healthcareInstitutionRepository.save(HealthcareInstitutionMock.get());
        Exam exam = examRepository.save(ExamMock.get(healthcare));

        mockMvc.perform(MockMvcRequestBuilders.delete("/exam/" + exam.getId() + "/" + healthcare.getId())
            .content(TestingUtilities.json(exam))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.delete("/exam/" + exam.getId() + "/" + healthcare.getId())
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.errorMessage")
                    .value("Não foi possível localizar esse registro!")
            )
            .andReturn();
    }
}

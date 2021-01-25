package com.testExam.controllers;

import com.testExam.dto.HealthcareInstitutionDTO;
import com.testExam.dto.HealthcareInstitutionDTOMock;
import com.testExam.entity.HealthcareInstitution;
import com.testExam.entity.HealthcareInstitutionMock;
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

import static com.testExam.exception.Message.CNPJ_ALREADY_EXISTS;
import static com.testExam.exception.Message.REGISTRATION_NOT_LOCATED;

@SpringBootTest
@AutoConfigureMockMvc
public class HealthcareInstitutionControllerTest {

    @Autowired
    private HealthcareInstitutionRepository healthcareInstitutionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void down() {
        healthcareInstitutionRepository.deleteAll();
    }

    @Test
    public void testNullFields() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/healthcare-institution")
            .content("{}")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andReturn();
    }

    @Test
    public void testValidIfNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/healthcare-institution/234523")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.errorMessage")
                    .value(REGISTRATION_NOT_LOCATED.getMessage())
            )
            .andReturn();
    }

    @Test
    public void testGet() throws Exception {
        HealthcareInstitution health = healthcareInstitutionRepository.save(HealthcareInstitutionMock.get());
        mockMvc.perform(MockMvcRequestBuilders.get("/healthcare-institution/" + health.getId())
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(health.getName()))
            .andReturn();
    }

    @Test
    public void testSave() throws Exception {
        HealthcareInstitutionDTO dto = HealthcareInstitutionDTOMock.get();
        mockMvc.perform(MockMvcRequestBuilders.post("/healthcare-institution")
            .content(TestingUtilities.json(dto))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
            .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.post("/healthcare-institution")
            .content(TestingUtilities.json(dto))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isConflict())
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.errorMessage")
                    .value(CNPJ_ALREADY_EXISTS.getMessage())
            )
            .andReturn();
    }
}

package com.testExam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HealthcareInstitutionDTO {

    private Long id;

    @NotBlank
    @CNPJ(message = "CNPJ informado não é valido!")
    private String CNPJ;

    @NotBlank
    private String name;
}

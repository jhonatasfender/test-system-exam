package com.testExam.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class HealthcareInstitutionViewDTO extends HealthcareInstitutionDTO {

    @NotNull
    private Integer pixeonCoins;
}

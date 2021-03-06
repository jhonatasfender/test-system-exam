package com.testExam.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HealthcareInstitution {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @CNPJ(message = "CNPJ informado não é valido!")
    private String CNPJ;

    @NotBlank
    private String name;

    @NotNull
    private Integer pixeonCoins;
}

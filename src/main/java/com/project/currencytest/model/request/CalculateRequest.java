package com.project.currencytest.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalculateRequest {
    @NotBlank(message = "'sourceCurrency' No debe ser vacio!")
    @Size(min = 3, max = 50, message = "'sourceCurrency' debe tener m&aacute;s de 50 caracteres")
    private String sourceCurrency;

    @NotBlank(message = "'targetCurrency' No debe ser vacio!")
    @Size(min = 3, max = 50, message = "'targetCurrency' debe tener m&aacute;s de 50 caracteres")
    private String targetCurrency;

    private Double amount;
}

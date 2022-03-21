package com.project.currencytest.model.request;

import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExchangeRateRequest {

    @NotBlank(message = "'sourceCurrency' No debe ser vacio!")
    @Size(min = 3, max = 50, message = "'sourceCurrency' debe tener m&aacute;s de 50 caracteres")
    private String sourceCurrency;

    @NotBlank(message = "'targetCurrency' No debe ser vacio!")
    @Size(min = 3, max = 50, message = "'targetCurrency' debe tener m&aacute;s de 50 caracteres")
    private String targetCurrency;

    private Double exchangeRate;
}
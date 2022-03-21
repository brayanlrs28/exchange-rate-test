package com.project.currencytest.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateExchangeRateRequest  extends ExchangeRateRequest{
    private Integer idExchangeRate;
}
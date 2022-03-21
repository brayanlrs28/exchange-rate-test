package com.project.currencytest.service;

import com.project.currencytest.model.entity.ExchangeRate;
import com.project.currencytest.model.request.CalculateRequest;
import com.project.currencytest.model.request.ExchangeRateRequest;
import com.project.currencytest.model.request.UpdateExchangeRateRequest;
import io.reactivex.Maybe;
import io.reactivex.Single;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ExchangeRateService {
    Maybe<ExchangeRate> register(ExchangeRateRequest request);
    Maybe<ExchangeRate> update(UpdateExchangeRateRequest request);
    Single<List<ExchangeRate>> listExchanges();

    Single<Object> calculate(CalculateRequest request, HttpServletRequest httpRequest);
}
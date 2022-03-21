package com.project.currencytest.service.impl;

import com.project.currencytest.model.entity.CurrencyExchanges;
import com.project.currencytest.model.entity.ExchangeRate;
import com.project.currencytest.model.request.CalculateRequest;
import com.project.currencytest.model.request.ExchangeRateRequest;
import com.project.currencytest.model.request.UpdateExchangeRateRequest;
import com.project.currencytest.model.response.CalculateResponse;
import com.project.currencytest.repository.CurrencyExchangesRepository;
import com.project.currencytest.repository.ExchangeRateRepository;
import com.project.currencytest.service.ExchangeRateService;
import com.project.currencytest.util.JwtUtil;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    ExchangeRateRepository repository;

    @Autowired
    CurrencyExchangesRepository currencyExchangesRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Maybe<ExchangeRate> register(ExchangeRateRequest request) {
        return Maybe.just(request).map(r -> repository.save(buildEntity.apply(r)));
    }

    Function<ExchangeRateRequest, ExchangeRate> buildEntity = request -> {
        ExchangeRate entity = new ExchangeRate();
        entity.setExchangeRate(request.getExchangeRate());
        entity.setCreateDate(new Date());
        entity.setSourceCurrency(request.getSourceCurrency());
        entity.setTargetCurrency(request.getTargetCurrency());
        return entity;
    };

    @Override
    public Maybe<ExchangeRate> update(UpdateExchangeRateRequest request) {
        return Maybe.just(request)
                .map(r -> repository.findById(r.getIdExchangeRate())
                        .map(data -> {
                            data.setExchangeRate(request.getExchangeRate());
                            data.setSourceCurrency(request.getSourceCurrency());
                            data.setTargetCurrency(request.getTargetCurrency());
                            repository.save(data);
                            return data;
                        }).orElse(new ExchangeRate()));
    }

    @Override
    public Single<List<ExchangeRate>> listExchanges() {
        return Single.just(repository.findAll()).onErrorReturn(throwable -> new ArrayList<>());
    }

    @Override
    public Single<Object> calculate(CalculateRequest request, HttpServletRequest httpRequest) {
        return Single.just(repository.findBySourceCurrencyAndTargetCurrency(
                request.getSourceCurrency(), request.getTargetCurrency()).orElse(new ExchangeRate()))
                .map(exchangeRate -> Single.just(buildResponse.apply(exchangeRate, request))
                        .map(calculateResponse -> {
                            if (calculateResponse.getConvertedAmount() != null) {
                                String userName = validate(httpRequest);
                                this.currencyExchangesRepository.save(buildExchanges(exchangeRate, request,
                                        calculateResponse.getConvertedAmount(), userName));
                            }
                            return calculateResponse;
                        }));
    }

    BiFunction<ExchangeRate, CalculateRequest, CalculateResponse> buildResponse = (x, z) -> {
        CalculateResponse response = new CalculateResponse();
        if (x.getExchangeRateId() != null) {
            Double converted = z.getAmount() * x.getExchangeRate();
            response.setValidCalculate(true);
            response.setConvertedAmount(converted);
        }
        return response;
    };

    private CurrencyExchanges buildExchanges(ExchangeRate rate, CalculateRequest request,
                                             Double converted, String user) {
        CurrencyExchanges exchanges = new CurrencyExchanges();
        exchanges.setExchangeRate(rate.getExchangeRate());
        exchanges.setCreateDate(new Date());
        exchanges.setSourceCurrency(rate.getSourceCurrency());
        exchanges.setTargetCurrency(rate.getTargetCurrency());
        exchanges.setAfterAmount(request.getAmount());
        exchanges.setBeforeAmount(converted);
        exchanges.setUser(user);
        return exchanges;
    }

    private String validate(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            userName = jwtUtil.extractUsername(token);
        }
        return userName;
    }
}
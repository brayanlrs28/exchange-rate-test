package com.project.currencytest.controller;

import com.project.currencytest.model.entity.ExchangeRate;
import com.project.currencytest.model.request.AuthRequest;
import com.project.currencytest.model.request.CalculateRequest;
import com.project.currencytest.model.request.ExchangeRateRequest;
import com.project.currencytest.model.request.UpdateExchangeRateRequest;
import com.project.currencytest.service.ExchangeRateService;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/v1/exchange")
@Controller
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService service;


    @RequestMapping(value = "/calculate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Single<Object> calculate(@Valid @RequestBody CalculateRequest request, HttpServletRequest httpRequest) {
        log.info("*** Start ExchangeRateController calculate ***");
        return service.calculate(request, httpRequest);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Maybe<ExchangeRate> registerExchangeRate(@Valid @RequestBody ExchangeRateRequest request) {
        log.info("*** Start ExchangeRateController registerExchangeRate ***");
        return service.register(request);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Maybe<ExchangeRate> updateExchangeRate(@Valid @RequestBody UpdateExchangeRateRequest request) {
        log.info("*** Start ExchangeRateController registerExchangeRate ***");
        return service.update(request);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Single<List<ExchangeRate>> listAll() {
        log.info("*** Start ExchangeRateController registerExchangeRate ***");
        return service.listExchanges();
    }
}
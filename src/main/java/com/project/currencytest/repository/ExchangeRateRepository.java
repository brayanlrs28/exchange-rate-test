package com.project.currencytest.repository;

import com.project.currencytest.model.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {
    Optional<ExchangeRate> findBySourceCurrencyAndTargetCurrency(String sourceCurrency, String targetcurrency);
}
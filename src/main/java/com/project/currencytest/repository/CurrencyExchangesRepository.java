package com.project.currencytest.repository;

import com.project.currencytest.model.entity.CurrencyExchanges;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangesRepository extends JpaRepository<CurrencyExchanges, Integer> {
}

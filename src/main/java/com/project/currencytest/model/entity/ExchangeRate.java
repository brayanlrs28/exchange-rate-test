package com.project.currencytest.model.entity;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Entity exchange_rate.
 *
 * @author lr.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "exchange_rate")
public class ExchangeRate {
    @Id
    @Column(name = "exchange_rate_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer exchangeRateId;

    @Column(name = "source_currency")
    private String sourceCurrency;

    @Column(name = "target_currency")
    private String targetCurrency;

    @Column(name = "exchange_rate")
    private Double exchangeRate;

    @Column(name = "create_date")
    private Date createDate;
}
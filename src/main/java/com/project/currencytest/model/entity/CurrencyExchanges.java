package com.project.currencytest.model.entity;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity exchange_rate.
 *
 * @author lr.
 */
@Setter
@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "currency_exchanges")
public class CurrencyExchanges {
    @Id
    @Column(name = "currency_exchanges_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer currencyExchangesId;

    @Column(name = "before_amount")
    private Double beforeAmount;

    @Column(name = "after_amount")
    private Double afterAmount;

    @Column(name = "user_name")
    private String user;

    @Column(name = "source_currency")
    private String sourceCurrency;

    @Column(name = "target_currency")
    private String targetCurrency;

    @Column(name = "exchange_rate")
    private Double exchangeRate;

    @Column(name = "create_date")
    private Date createDate;
}
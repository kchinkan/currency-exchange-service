package com.kevin.microservices.currencyexchangeservice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "CURRENCY_EXCHANGE")
public class CurrencyExchange {
    @Id
    private Long id;

    @Column(name = "currency_from")
    private String from;
    @Column(name = "currency_to")
    private String to;
    @Column(precision = 16, scale = 4)
    private BigDecimal conversionMultiplier;

    private String environment;

    public CurrencyExchange() {
    }

    public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiplier) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiplier = conversionMultiplier;
    }
}

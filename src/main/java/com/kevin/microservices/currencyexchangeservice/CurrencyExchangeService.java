package com.kevin.microservices.currencyexchangeservice;

public interface CurrencyExchangeService {
    public CurrencyExchange getFromTo(String from, String to);
}

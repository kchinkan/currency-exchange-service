package com.kevin.microservices.currencyexchangeservice;

public interface CurrencyExchangeService {
    CurrencyExchange getFromTo(String from, String to);
}

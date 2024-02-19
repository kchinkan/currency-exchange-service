package com.kevin.microservices.currencyexchangeservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
    private final CurrencyExchangeRepository repository;
    @Override
    public CurrencyExchange getFromTo(String from, String to) {
        final Optional<CurrencyExchange> result = repository.findByFromAndTo(from, to);

        return result.orElseThrow(() -> new NoSuchElementException(format("No rate found for [from: %s, to: %s]", from, to)));
    }
}

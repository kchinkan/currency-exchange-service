package com.kevin.microservices.currencyexchangeservice;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.NoSuchElementException;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {
    private final Environment environment;
    private final CurrencyExchangeRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getCurrencyExchange(@PathVariable String from, @PathVariable String to) {
        final Optional<CurrencyExchange> result = repository.findByFromAndTo(from, to);

        CurrencyExchange response = result.orElseThrow(() -> new NoSuchElementException(format("No rate found for [from: %s, to: %s]", from, to)));

        final String port = environment.getProperty("local.server.port");
        response.setEnvironment(port);
        return response;
    }
}

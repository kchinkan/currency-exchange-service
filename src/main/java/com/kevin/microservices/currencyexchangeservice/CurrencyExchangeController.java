package com.kevin.microservices.currencyexchangeservice;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {
    private final Environment environment;
    private final CurrencyExchangeService service;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ResponseEntity<CurrencyExchange> getCurrencyExchange(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange response = service.getFromTo(from, to);
        response.setEnvironment(environment.getProperty("local.server.port"));
        
        return ResponseEntity.ok(response);
    }
}

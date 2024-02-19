package com.kevin.microservices.currencyexchangeservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyExchangeServiceTest {

    @Mock
    private CurrencyExchangeRepository repository;

    private CurrencyExchangeService service;

    private static final Map<String[], BigDecimal> expectedData = new HashMap<>();
    private static final String ERROR_COUNTRY = "ABC";

    static {
        expectedData.put(new String[] {"USD", "EUR"}, BigDecimal.valueOf(0.9309));
        expectedData.put(new String[] {"USD", "CHF"}, BigDecimal.valueOf(0.8704));
        expectedData.put(new String[] {"USD", "GBP"}, BigDecimal.valueOf(0.7978));
        expectedData.put(new String[] {"USD", "JPY"}, BigDecimal.valueOf(148.67));
    }

    @BeforeEach
    public void setUp() {
        service = new CurrencyExchangeServiceImpl(repository);
    }

    @Test
    public void testFindByFromAndTo() {
        expectedData.forEach((countries, multiplier) -> {
            CurrencyExchange value = new CurrencyExchange();
            value.setFrom(countries[0]);
            value.setTo(countries[1]);
            value.setConversionMultiplier(multiplier);
            when(repository.findByFromAndTo(countries[0], countries[1])).thenReturn(Optional.of(value));
        });
        expectedData.forEach((countries, multiplier) -> {
            CurrencyExchange result = service.getFromTo(countries[0], countries[1]);
            assertEquals(countries[0], result.getFrom());
            assertEquals(countries[1], result.getTo());
            assertEquals(multiplier, result.getConversionMultiplier());
        });
    }

    @Test
    public void testFindByFromAndToThrowsException() {
        when(repository.findByFromAndTo(anyString(), anyString())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.getFromTo(ERROR_COUNTRY, ERROR_COUNTRY));
    }
}

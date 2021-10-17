package com.example.demo.calculator.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class DollarCalculatorTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private DollarCalculator dollarxCalculator;

    @Test
    public void dollerCalculatorTest() {
        Mockito.when(marketApi.connect()).thenReturn(3000);
        System.out.println(marketApi.connect());
        dollarxCalculator.init();

        int sum = dollarxCalculator.sum(10, 10);
        int minus = dollarxCalculator.minus(10, 10);

        Assertions.assertEquals(60000, sum);
        Assertions.assertEquals(0, minus);
    }
}
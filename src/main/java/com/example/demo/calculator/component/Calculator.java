package com.example.demo.calculator.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Calculator {

    private ICalculator iCalculator;

    public int sum(int x, int y) {
        return this.iCalculator.sum(x, y);
    }

    public int minus(int x, int y) {
        return this.iCalculator.minus(x, y);
    }
}

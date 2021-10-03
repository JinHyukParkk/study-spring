package com.example.demo.calculator.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DollarCalculator implements ICalculator {

    private int price = 1;

    // 해당 클래스가 Component라면 생성자에 받는 참조 객체도 Component
    private final MarketApi marketApi;

    @Override
    public void init() {
        this.price = marketApi.connect();
    }

    @Override
    public int sum(int x, int y) {
        x *= price;
        y *= price;

        return x + y;
    }

    @Override
    public int minus(int x, int y) {
        x *= price;
        y *= price;

        return x - y;
    }
}

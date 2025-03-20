package com.cafe.coffee;

import com.cafe.api.CoffeeService;

public class CoffeeBrewer implements CoffeeService {
    @Override
    public void makeCoffee(String customerName) {
        System.out.println(" Coffee Brewer : Made a coffee for  " + customerName);
    }
}

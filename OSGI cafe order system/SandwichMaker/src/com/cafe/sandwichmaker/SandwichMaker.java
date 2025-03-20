package com.cafe.sandwichmaker;


import com.cafe.api.SandwichService;

public class SandwichMaker implements SandwichService {

	@Override
	public void makeSandwich(String customerName, String sandwichType) {
		System.out.println(" Sandwich maker :: Made " + sandwichType.toLowerCase()  + "sandwich for "+ customerName +".");
	}
}
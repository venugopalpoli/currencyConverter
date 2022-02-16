package com.currency.converter.currencyconverter;

import org.springframework.stereotype.Component;

@Component
public class CurrencyRateCalculation {

	double getConvertedAmount(double amount, double rate){
		double convertedAmount = amount * rate;
		convertedAmount = Math.round(convertedAmount * 100.00) / 100.00;
		return convertedAmount;
	}
}

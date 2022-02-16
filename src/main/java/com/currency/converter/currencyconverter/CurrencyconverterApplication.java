package com.currency.converter.currencyconverter;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CurrencyconverterApplication implements CommandLineRunner {
	
	final Logger LOGGER = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(CurrencyconverterApplication.class, args);
	}
	
	@Autowired
	private CurrencyRateProvider currencyRateFileReader;
	
	@Autowired
	private CurrencyRateCalculation currencyRateCalculation;

	 
	@Override
	public void run(String... args) throws Exception 
	{
		 LOGGER.info("Executed.. ");
		 Scanner scanner = new Scanner(System.in);
		 LOGGER.info("Source currency amout in format x.yy : ");
		 
		 //The amount of source country is UK (GBP) - as per requirement
		 double amount = scanner.nextDouble();
		 LOGGER.info("Source currency code : ");
		 
		//The currency of amount of source country is GBP - as per requirement
		 String sourceCode = scanner.next();
		 LOGGER.info("Target currency code : ");
		 String targetCode = scanner.next();
		    
		 CurrencyRateRecord currencyRateRecord = currencyRateFileReader.getRate(targetCode);
		 
		 double convertedAmount = currencyRateCalculation.getConvertedAmount(amount, currencyRateRecord.getRate());
		 LOGGER.info("Converted Amount : " + convertedAmount);
		 LOGGER.info("Target Country : " + currencyRateRecord.getCountry());
	}
}

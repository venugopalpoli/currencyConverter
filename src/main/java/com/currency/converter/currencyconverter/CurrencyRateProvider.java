package com.currency.converter.currencyconverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CurrencyRateProvider {
	
	final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private Map<String, CurrencyRateRecord> currencyRatesMap;
	
	public CurrencyRateProvider(){
		currencyRatesMap = new HashMap<String, CurrencyRateRecord>();
	}
	
	private void readAndLoadData(){

		  try{
			  Stream<String> lines = Files.newBufferedReader(Paths.get("currencyRatesForGBP.csv")).lines();
			  lines.forEach(line->{
				  String[] record = line.split(",");
				  CurrencyRateRecord currencyRateRecord = new CurrencyRateRecord();
				  currencyRateRecord.setCountry(record[0]);
				  currencyRateRecord.setName(record[1]);
				  currencyRateRecord.setCode(record[2]);
				  currencyRateRecord.setRate(new Double(record[3]));
				  
				  currencyRatesMap.put(record[2],currencyRateRecord);
			  });
			  LOGGER.info("Data read from file and loaded into local cache");
		  }catch (IOException e){
		    LOGGER.error("IOException", e);
		  }
	}
	
	CurrencyRateRecord getRate(String currencyCode){
		
		if(currencyRatesMap.size()==0){
			readAndLoadData();
		}
			
		LOGGER.info("Get Rate for given target currency code");
		return currencyRatesMap.get(currencyCode);
	}
	

}

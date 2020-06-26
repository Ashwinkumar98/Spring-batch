package com.customer.batch.processor;

import java.util.HashMap;
import java.util.Map;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import com.customer.batch.modal.Customer;


@Component
public class Processor implements ItemProcessor<Customer, Customer> {
	
	/*
	 * use country code change logic in processor 
	 * */
	
	public static final Map<String,String> COUNTRY_CODE = new HashMap<>();
	
	public Processor() {
		COUNTRY_CODE.put("1", "USA");
		COUNTRY_CODE.put("2", "IND");
		COUNTRY_CODE.put("3", "CND");
	}
	
	@Override
	public Customer process(Customer item) throws Exception {
		String countryCode = item.getCountryCode();
		String countryName =COUNTRY_CODE.get(countryCode);
		item.setCountryCode(countryName);
		return item;
	}

}

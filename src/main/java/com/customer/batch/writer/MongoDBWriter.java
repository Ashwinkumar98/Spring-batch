package com.customer.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.customer.batch.modal.Customer;
import com.customer.batch.repo.CustomerDao;

@Component
public class MongoDBWriter implements ItemWriter<Customer> {
	
	/*
	 * Writer writes data into mongo db 
	 * */
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	public void write(List<? extends Customer> items) throws Exception {
		customerDao.saveAll(items);
	}
}

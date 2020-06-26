package com.customer.batch.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.customer.batch.modal.Customer;

@Repository
public interface CustomerDao extends MongoRepository<Customer, Integer>{

}

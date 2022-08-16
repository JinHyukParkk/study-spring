package com.example.minireceiver.repository;

import com.example.minireceiver.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    @Query("{firstName: '?'}")
    Customer findCustomerByFirstName(String firstName);

    public long count();
}

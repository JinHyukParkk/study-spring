package com.example.minireceiver.service;

import com.example.minireceiver.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public class MongoService {

    @Autowired
    MongoRepository mongoRepository;

    public void create() {
        Customer customer = new Customer("park", "jinhyuk");

        mongoRepository.save(customer);
    }
}

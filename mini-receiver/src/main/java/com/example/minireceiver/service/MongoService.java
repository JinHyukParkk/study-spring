package com.example.minireceiver.service;

import com.example.minireceiver.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void create() {
        Customer customer = new Customer("park", "jinhyuk");

        mongoTemplate.insert(customer);

        List<Customer> result = mongoTemplate.find(Query.query(Criteria.where("firstName").is("park")), Customer.class);

        result.forEach(System.out::println);
    }
}

package com.genisys.springReactive.service;

import com.genisys.springReactive.dao.CustomerDao;
import com.genisys.springReactive.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> loadAllCustomer(){
        long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCoCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total execution "+ (start-end));
        return customers;
    }

    public Flux<Customer> loadAllCustomerStream(){
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCoCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("Total execution "+ (start-end));
        return customers;
    }
}

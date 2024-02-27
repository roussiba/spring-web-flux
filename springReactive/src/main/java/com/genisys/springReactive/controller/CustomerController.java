package com.genisys.springReactive.controller;

import com.genisys.springReactive.dto.Customer;
import com.genisys.springReactive.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAlCustomers(){
        return customerService.loadAllCustomer();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAlCustomersStream(){
        return customerService.loadAllCustomerStream();
    }
}

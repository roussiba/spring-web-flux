package com.genisys.springReactive.handler;

import com.genisys.springReactive.dao.CustomerDao;
import com.genisys.springReactive.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomer(ServerRequest request){
        Flux<Customer> customerFlux = customerDao.getCoCustomerList();
        return ServerResponse.ok().body(customerFlux, Customer.class);
    }


    public Mono<ServerResponse> loadCustomerStream(ServerRequest request){
        Flux<Customer> customerFlux = customerDao.getCoCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerFlux, Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request){
        int customerId = Integer.parseInt(request.pathVariable("input"));
        Mono<Customer> customerMono = customerDao.getCoCustomerList().filter(c -> c.id() == customerId).next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customer = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customer.map(dto -> dto.id() + ":" + dto.name());
        return ServerResponse.ok().body(saveResponse, String.class);
    }

}

package com.genisys.springReactive.router;

import com.genisys.springReactive.handler.CustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;
    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/router/customers", handler::loadCustomer)
                .GET("/router/customers/stream", handler::loadCustomerStream)
                .GET("/router/customers/{input}", handler::findCustomer)
                .POST("/router/customers/save", handler::saveCustomer)
                .build();
    }
}

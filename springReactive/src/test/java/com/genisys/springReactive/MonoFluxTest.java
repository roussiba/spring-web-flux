package com.genisys.springReactive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
public class MonoFluxTest {

    @Test
    public void testMono(){
        Mono<?> stringMono = Mono.just("Genisys")
                .then(Mono.error(new RuntimeException("Exception occured")))
                .log();
        stringMono.subscribe(System.out::println, (e)->System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux(){
        Flux<String> fluxString = Flux.just("Spring", "reactive", "test")
                .concatWithValues("AWS")
                .concatWith(Mono.error(new RuntimeException("Exception occured")))
                .log();
        fluxString.subscribe(System.out::println);
    }
}

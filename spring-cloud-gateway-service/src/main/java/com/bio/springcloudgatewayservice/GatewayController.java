package com.bio.springcloudgatewayservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GatewayController {

    @GetMapping("/countriesfallback")
    public Mono<String> countries() {
        return Mono.just("Countries API is taking too long to respond or is down. Please try again later.");
    }

    @GetMapping("/jokefallback")
    public Mono<String> joke() {
        return Mono.just("Joke API is taking too long to respond or is down. Please try again later.");
    }

    @GetMapping("/norrisfallback")
    public Mono<String> chuck() {
        return Mono.just("Chuck Joke API is taking too long to respond or is down. Please try again later.");
    }
}

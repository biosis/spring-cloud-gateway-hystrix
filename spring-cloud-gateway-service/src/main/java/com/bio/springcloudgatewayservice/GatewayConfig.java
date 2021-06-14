package com.bio.springcloudgatewayservice;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableHystrix
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r
                        .path("/all")
                        .filters(f -> f
                                .addRequestHeader("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
                                .addRequestHeader("x-rapidapi-key", "59604f2023msh667fbfaa6f774e4p169495jsne158bf51c6e1")
                                .hystrix(config -> config
                                        .setName("countries-service")
                                        .setFallbackUri("forward:/countriesfallback")
                                )
                        )
                        .uri("https://restcountries-v1.p.rapidapi.com")
                )

                .route("path_route", r -> r
                        .path("/jokes/random")
                        .filters(f -> f
                                .addRequestHeader("x-rapidapi-host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                                .addRequestHeader("x-rapidapi-key", "59604f2023msh667fbfaa6f774e4p169495jsne158bf51c6e1")
                                .hystrix(config -> config
                                        .setName("chuck-service")
                                        .setFallbackUri("forward:/norrisfallback")
                                )
                        )
                        .uri("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                )

                .route("path_route", r -> r
                        .path("/v1/joke")
                        .filters(f -> f
                                .addRequestHeader("x-rapidapi-host", "joke3.p.rapidapi.com")
                                .addRequestHeader("x-rapidapi-key", "59604f2023msh667fbfaa6f774e4p169495jsne158bf51c6e1")
                                .hystrix(config -> config
                                        .setName("joke-service")
                                        .setFallbackUri("forward:/jokefallback")
                                )
                        )
                        .uri("https://joke3.p.rapidapi.com")
                )

                .route("path_route", r -> r.path("/get")
                        .uri("http://httpbin.org"))
                .route("hystrix_route", r -> r.host("*.hystrix.org")
                        .filters(f -> f.hystrix(c -> c.setName("slowcmd")))
                        .uri("http://httpbin.org"))
                /*.route("limit_route", r -> r
                        .host("*.limited.org").and().path("/anything/**")
                        //.filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
                        .uri("http://httpbin.org"))*/
                .build();
    }

}

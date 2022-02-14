package com.example.demo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
//                .route("Greet-client", rt -> rt.path("/greeting/**")
//                        .uri("http://localhost:8070/"))
//                .route("Greet-server", rt -> rt.path("/hello/**")
//                        .uri("http://localhost:8071/"))
                .route("Customer-client", rt -> rt.path("/customerhello/**")
                        .uri("http://localhost:8069/"))
                .route("Account-server", rt -> rt.path("/account/**")
                        .uri("http://localhost:8078/"))
                .build();

    }
}

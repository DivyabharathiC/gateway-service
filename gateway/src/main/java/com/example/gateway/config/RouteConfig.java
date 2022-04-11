package com.example.gateway.config;

import com.example.gateway.filter.JwtAuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder, JwtAuthFilter filter) {
        return routeLocatorBuilder.routes()

                .route("user-service", rt -> rt.path("/api/v1/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:3005/"))
                .route("post-service", rt -> rt.path("/api/v1/posts/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:3010/"))
                .route("comment-service", rt -> rt.path("/api/v1/post/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:3015/"))
                .route("like-service", rt -> rt.path("/api/v1/postsOrComments/*/likes/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:3020/"))
                .route("authentication", rt -> rt.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8075/"))
                .build();


    }
}

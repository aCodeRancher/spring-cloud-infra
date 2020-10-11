package com.codespacelab.edge;

import com.codespacelab.edge.filter.HeaderFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LBConfig {

    @Bean
    public RouteLocator routeLocator (RouteLocatorBuilder builder, HeaderFilter headerFilter){
        return builder.routes()
                .route( r -> r.path("/burger-api/user/**")
                        .filters(f -> f.rewritePath("/burger-api/(?<segment>.*)", "/${segment}")
                                       .filter( headerFilter.apply(new HeaderFilter.Config()))
                                        .circuitBreaker( c->c.setName("userCB")
                                                 .setFallbackUri("forward:/user-failover").setRouteId("user-failover")))
                        .uri("lb://USER")
                        .id("user"))
                
                .route( r-> r.path("/burger-api/order/**")
                        .filters(f->f.rewritePath("/burger-api/(?<segment>.*)", "/${segment}")
                                 .filter(headerFilter.apply(new HeaderFilter.Config()))
                            )
                .uri("lb://ORDER")
                .id("order"))

                .route(r -> r.path( "/user-failover").uri("lb://user-failover").id("user-failover"))
                .build();
    }
}

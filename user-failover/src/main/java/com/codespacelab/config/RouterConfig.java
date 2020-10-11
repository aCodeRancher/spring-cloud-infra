package com.codespacelab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction userRoute(UserFailoverHandler userFailoverHandler){
        return route(GET("/user-failover").and(accept(MediaType.APPLICATION_JSON)),
                      userFailoverHandler::getValidated);
    }
}

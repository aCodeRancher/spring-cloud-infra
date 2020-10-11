package com.codespacelab.config;

import com.codespacelab.model.UserDto;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

@Component
public class UserFailoverHandler {


    public Mono<ServerResponse> getValidated(ServerRequest request){
         return ServerResponse.ok()
                 .contentType(MediaType.APPLICATION_STREAM_JSON)
                 .body( Mono.just(false), Boolean.class);
    }

}

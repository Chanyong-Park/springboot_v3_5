package com.cooldragon.webflux.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/hello")
@Tag(name = "Hello API", description = "Hello API")
public class HelloController {

    @Value("${application.current-profile:notexist}")
    private String value;

    @GetMapping("/")
    @Operation(summary = "hello", description = "hello description")
    public Mono<String> hello() {
        
        return Mono.just("hello!, " + value + ", MY_TYPE: " + com.cooldragon.common.def.enums.EnumCollection.MY_TYPE.A.getName());
    }
}

package com.cooldragon.webflux.controllers;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin API", description = "Admin API")
public class AdminController {

    @GetMapping("/get")
    @Operation(summary = "get", description = "get description")
    public Mono<String> get() {
        return Mono.just("get!" + LocalDateTime.now().toLocalTime());
    }
}

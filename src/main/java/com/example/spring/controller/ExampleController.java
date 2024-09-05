package com.example.spring.controller;

import com.example.spring.openApi.ExampleControllerOpenApi;
import com.example.spring.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(value = "/api/example", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ExampleController implements ExampleControllerOpenApi {

    private final ExampleService service;

    @GetMapping("/hello/{value}")
    public ResponseEntity<String> getHello(@PathVariable String value) {
        return ResponseEntity.ok(service.getHello(value));
    }

}

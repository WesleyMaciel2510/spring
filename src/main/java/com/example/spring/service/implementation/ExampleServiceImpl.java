package com.example.spring.service.implementation;

import com.example.spring.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleServiceImpl implements ExampleService {

    //private final ExampleRepository repository;

    @Override
    public String getHello(String value) {
        // return repository.getHello(value);
        return "Hello " + value;
    }
}

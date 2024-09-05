package com.example.spring.repository;

//@Repository
public interface ExampleRepository {

    //@Query("SELECT 'Hello ' || ?1 FROM DUAL")
    String getHello(String value);

}

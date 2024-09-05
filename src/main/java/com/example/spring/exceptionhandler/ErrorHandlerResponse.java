package com.example.spring.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorHandlerResponse {
    private int code;
    private String message;
    private LocalDateTime createdAt;
    private StackTraceElement[] stacktrace;
}

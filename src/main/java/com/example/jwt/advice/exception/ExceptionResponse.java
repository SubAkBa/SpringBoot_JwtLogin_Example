package com.example.jwt.advice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
    private LocalDateTime timeStamp;
    private String msg;
    private String details;
}

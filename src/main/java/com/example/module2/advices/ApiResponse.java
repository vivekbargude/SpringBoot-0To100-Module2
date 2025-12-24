package com.example.module2.advices;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private LocalDateTime timeStamps;
    private T data;
    private ApiError error;

    public ApiResponse(T data) {
        this(); //Call to default constructor.
        this.data = data;
    }

    public ApiResponse() {
        this.timeStamps = LocalDateTime.now();
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }
}

package com.example.morphdb.usercase.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ApiResponse<T> {
    private String message;
    private LocalDateTime timeStamp;
    private T data;

    public ApiResponse(String message, LocalDateTime timeStamp, T data) {
        this.message = message;
        this.timeStamp = LocalDateTime.now();
        this.data = data;
    }
}

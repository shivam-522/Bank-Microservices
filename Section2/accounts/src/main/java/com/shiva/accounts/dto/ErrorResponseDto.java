package com.shiva.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
// When ever error comes from our microservice we need tp send error in proper formate to the client
public class ErrorResponseDto {

    private String apiPath; // This is the api path my client application trying to invoke

    private HttpStatus errorCode;

    private String errorMessage;


    private LocalDateTime errorTime;




}

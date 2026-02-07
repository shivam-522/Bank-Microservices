package com.shiva.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema( name="ErrorResponse",
        description = "Schema to hold successful response information"
)
@Data @AllArgsConstructor
// When ever error comes from our microservice we need tp send error in proper formate to the client
public class ErrorResponseDto {

    @Schema(description = "API Path Invoked By client"
    )
    private String apiPath; // This is the api path my client application trying to invoke

    @Schema(description = "Error code representing the error happened"
    )
    private HttpStatus errorCode;

    @Schema(description = "Error message representing the error happened"
    )
    private String errorMessage;


    @Schema(description = "Time representing when the error happened"
    )
    private LocalDateTime errorTime;




}

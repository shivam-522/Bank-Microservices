package com.shiva.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema( name="Response",
        description = "Schema to hold successful response information"
)
@Data
@AllArgsConstructor
// This class is for sending the response to the client with status code in success case
public class ResponseDto {

    @Schema(
            description = "statusCode in the response"
    )
    private String statusCode;

    @Schema(
            description = "statusMsg in the response"
    )
    private String statusMsg;

}

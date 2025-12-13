package com.shiva.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// This class is for sending the response to the client with status code in success case
public class ResponseDto {

    private String statusCode;

    private String statusMsg;

}

package com.shiva.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    /** If any of the below Validation the mentioned message will be throw in the error **/
    @NotEmpty(message="Name can not be a null or empty") /** By this field we are making this field as mandatory**/
    @Size(min=5, max=30, message="The length of the customer name should be between 5 or 30")
    private String name;

    @NotEmpty(message="Name can not be a null or empty")
    @Email(message="Email address should be a valid value")
    private String email;

    @Pattern(regexp="(^$|[0-9]{10})",message="Mobile Number Must Be of 10 Digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}

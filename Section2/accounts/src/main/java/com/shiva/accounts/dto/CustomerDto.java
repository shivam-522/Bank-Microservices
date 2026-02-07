package com.shiva.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema( /** By using this annotation we can give a technical name to the schema/DTO classes a valid and meaningful name on swagger**/
    name="Customer",
    description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Name Of The Customer", example = "Shivam Samadhiya" /** By this we can add the description of each field on swagger**/
            )
    /** If any of the below Validation the mentioned message will be throw in the error **/
    @NotEmpty(message="Name can not be a null or empty") /** By this field we are making this field as mandatory**/
    @Size(min=5, max=30, message="The length of the customer name should be between 5 or 30")
    private String name;

    @Schema(
            description = "Email Of The Customer", example = "shivamsamadhiya@gmail.com"
    )
    @NotEmpty(message="Name can not be a null or empty")
    @Email(message="Email address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile Number Of The Customer", example = "7983021962"
    )
    @Pattern(regexp="(^$|[0-9]{10})",message="Mobile Number Must Be of 10 Digits")
    private String mobileNumber;

    @Schema(
            description = "Account details Of The Customer"
    )
    private AccountsDto accountsDto;
}

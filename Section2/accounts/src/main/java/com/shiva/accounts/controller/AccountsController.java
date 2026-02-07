package com.shiva.accounts.controller;

import com.shiva.accounts.constants.AccountsConstants;
import com.shiva.accounts.dto.CustomerDto;
import com.shiva.accounts.dto.ErrorResponseDto;
import com.shiva.accounts.dto.ResponseDto;
import com.shiva.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name="CURD REST Apis For Accounts in MyBank",
        description = "CRUD REST APIs in myBank to CREATE,UPDATE,FETCH AND DELETE account details"
)
@RestController
@RequestMapping(path = "/api", produces= MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated /** In order to use Input validation we need this controller, It tells Spring boot frame to perform validations on the all endpoints**/
public class AccountsController {

    private IAccountsService iAccountsService;

    @Operation( /** This is Swagger annotation for endpoint level summary and description**/
            summary="Create Account REST API",
            description = "REST API to create new Customer & Account inside MyBank"
    )
    @ApiResponse( /** This is used to take the control on the visibility of the response body on swagger**/
            responseCode = "201", /** Here we are override the default code 200 with the actual code 201 on swagger  **/
            description = "HTTP Status Created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto)
    {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)// This status will go in response header
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));// This will go in the response body
    }

    @Operation(
            summary="Fetch Account Details REST API",
            description = "REST API to Fetch  Customer & Account details based on a mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam /** Here we are not taking input data with the help of DTO/RequestBody so we need to apply validation at the controller only**/
                                                               @Pattern(regexp="(^$|[0-9]{10})",message="Mobile Number Must Be of 10 Digits")
                                                               String mobileNumber) {
        /** This method will  take the phone number as query parameter and return the customer details from database **/

        CustomerDto customerDto= iAccountsService.fetchAccount(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary="Update Account Details REST API",
            description = "REST API to Update  Customer & Account details based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
            responseCode ="200",
    description ="HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode ="500",
                    description ="HTTP Status Internal Server Error",
                    content=@Content(
                            schema=@Schema(implementation= ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode ="417",
                    description ="Exception Failed"
            )

     })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary="Delete Account & Customer Details REST API",
            description = "REST API to Delete Customer & Account details based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode ="200",
                    description ="HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode ="500",
                    description ="HTTP Status Internal Server Error"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp="(^$|[0-9]{10})",message="Mobile Number Must Be of 10 Digits")
                                                                String mobileNumber)
    {
        boolean isDeleted=iAccountsService.deleteAccount(mobileNumber);
        if(isDeleted)
        {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_DELETE));
        }

    }


}

package com.shiva.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue)
    {
        /** From here we are trying to return the error message that for the perticular mobile number user is not there in db**/
        /** In order to return all three values in the super we need to do some workaround as this ususlly take one string value as a argument**/
        super(String.format("%s not found with the given input data %s:'%s", resourceName,fieldName,fieldValue));
    }
}

package com.shiva.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)// Whenever this exception class called client will get 400 bad request status message
public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException(String message)
    {
        /** So whenever the object of this class will be come in picture this
         *  constructor will get the message and sam message we need to pass in super class i.e RuntimeException because we extends it**/
        super(message);
    }
}

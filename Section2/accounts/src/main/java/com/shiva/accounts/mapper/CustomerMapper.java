package com.shiva.accounts.mapper;

import com.shiva.accounts.dto.CustomerDto;
import com.shiva.accounts.entity.Customer;
/** If we want to add extra validations we can do that also like if we want to mask tha mobile, only want to
 *  show the last four digits we can do that here
 * **/
public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}

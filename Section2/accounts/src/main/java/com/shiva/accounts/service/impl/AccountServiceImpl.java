package com.shiva.accounts.service.impl;

import com.shiva.accounts.constants.AccountsConstants;
import com.shiva.accounts.dto.CustomerDto;
import com.shiva.accounts.entity.Accounts;
import com.shiva.accounts.entity.Customer;
import com.shiva.accounts.exception.CustomerAlreadyExistsException;
import com.shiva.accounts.mapper.CustomerMapper;
import com.shiva.accounts.repository.AccountsRepository;
import com.shiva.accounts.repository.CustomerRepository;
import com.shiva.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;// As we have used @AllArgsConstructor we we don't need to inject them by making constructor and not need to write@Autowired annotation by one annotation springboot will take care of it
    private CustomerRepository customerRepository;


    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer= CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber(customerDto.getMobileNumber());
       if(optionalCustomer.isPresent())
       {
           throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber"+customerDto.getMobileNumber());
       }
       customer.setCreatedAt(LocalDateTime.now());
       customer.setCreatedBy("Anonymous");
        Customer savedCustomer=customerRepository.save(customer);
        /** From the above line we are creating new customer and saving in db
         * and we are getting newly created customer id in return
         * now we need to create new account for this customer that is done in below line
         *  **/
        accountsRepository.save(createNewAccount(savedCustomer));/** By this way we are making link between account and customer**/



    }
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }

}

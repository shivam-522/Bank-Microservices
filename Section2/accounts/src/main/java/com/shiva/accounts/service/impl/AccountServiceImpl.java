package com.shiva.accounts.service.impl;

import com.shiva.accounts.constants.AccountsConstants;
import com.shiva.accounts.dto.AccountsDto;
import com.shiva.accounts.dto.CustomerDto;
import com.shiva.accounts.entity.Accounts;
import com.shiva.accounts.entity.Customer;
import com.shiva.accounts.exception.CustomerAlreadyExistsException;
import com.shiva.accounts.exception.ResourceNotFoundException;
import com.shiva.accounts.mapper.AccountsMapper;
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
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));

        Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Account","customerId",String.valueOf(customer.getCustomerId()))
        );

        CustomerDto customerDto=CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdate=false;
        AccountsDto accountsDto=customerDto.getAccountsDto();
        if(accountsDto !=null)
        {
            /** Here since our Account number is the primary key of Accounts table so directly we can use findById method that is available in Spring Data JPA Framework
             * Whenever call go for findById my spring data JPA framework will go for the accounts entity and check what is the primary key column  and then it will fetch the account details**/
            Accounts accounts=accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    ()->new ResourceNotFoundException("Account","AccountNumber",accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto,accounts);
            accounts=accountsRepository.save(accounts);

            Long customerId=accounts.getCustomerId();
            Customer customer=customerRepository.findById(customerId).orElseThrow(
                    ()->new ResourceNotFoundException("customer","customerID",customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdate=true;
        }
        return isUpdate;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer =customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());/** Since the customerId is not the primary key in the accounts
         table so we need to write this method manually it is not byDefault provided by SpringBoot **/
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


}

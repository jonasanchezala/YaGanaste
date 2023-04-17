package com.yaganaste.excercise5.services;

import com.yaganaste.excercise5.exceptions.CustomerUpdateException;
import com.yaganaste.excercise5.models.Customer;
import com.yaganaste.excercise5.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(rollbackOn = CustomerUpdateException.class)
    public void transfer(BigDecimal value, long customerSenderId, long customerReceiverId) {

        try {
            Customer customerSender = customerRepository.findById(customerSenderId).orElse(null);
            if (Objects.isNull(customerSender)) {
                throw new CustomerUpdateException("Customer Sender Doest Not Exist");
            }

            BigDecimal customerSenderBalanceAfterTransaction = customerSender.getBalance().subtract(value);

            if (customerSenderBalanceAfterTransaction.signum() == -1) {
                throw new CustomerUpdateException("Customer Sender Doest Not Have Enough Balance");
            }

            customerSender.setBalance(customerSenderBalanceAfterTransaction);
            Customer customerReceiver = customerRepository.findById(customerReceiverId).orElse(null);

            if (Objects.isNull(customerReceiver)) {
                throw new CustomerUpdateException("Customer Receiver Doest Not Exist");
            }

            customerReceiver.setBalance(customerReceiver.getBalance().add(value));

            customerRepository.save(customerSender);
            customerRepository.save(customerReceiver);
        } catch (Exception e) {
            throw new CustomerUpdateException(e.getMessage());
        }
    }
}
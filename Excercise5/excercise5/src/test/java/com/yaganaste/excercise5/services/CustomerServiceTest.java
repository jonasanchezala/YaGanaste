package com.yaganaste.excercise5.services;

import com.yaganaste.excercise5.exceptions.CustomerUpdateException;
import com.yaganaste.excercise5.models.Customer;
import com.yaganaste.excercise5.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void transfer_shouldTransferAmountFromSenderToReceiver() {
        // Given
        long senderId = 1L;
        long receiverId = 2L;
        BigDecimal amount = BigDecimal.TEN;

        Customer sender = new Customer(senderId, "Sender", BigDecimal.valueOf(20));
        Customer receiver = new Customer(receiverId, "Receiver", BigDecimal.ZERO);

        // When
        when(customerRepository.findById(senderId)).thenReturn(Optional.of(sender));
        when(customerRepository.findById(receiverId)).thenReturn(Optional.of(receiver));

        // Then
        customerService.transfer(amount, senderId, receiverId);
    }

    @Test
    void transfer_shouldThrowExceptionIfSenderDoesNotExist() {
        // Given
        long senderId = 1L;
        long receiverId = 2L;
        BigDecimal amount = BigDecimal.TEN;

        // When
        when(customerRepository.findById(senderId)).thenReturn(Optional.empty());

        // Then
        CustomerUpdateException exception = assertThrows(CustomerUpdateException.class,
                () -> customerService.transfer(amount, senderId, receiverId));
        assertEquals("Customer Sender Doest Not Exist", exception.getMessage());
    }

    @Test
    void transfer_shouldThrowExceptionIfReceiverDoesNotExist() {
        // Given
        long senderId = 1L;
        long receiverId = 2L;
        BigDecimal amount = BigDecimal.TEN;

        Customer sender = new Customer(senderId, "Sender", BigDecimal.valueOf(20));

        // When
        when(customerRepository.findById(senderId)).thenReturn(Optional.of(sender));
        when(customerRepository.findById(receiverId)).thenReturn(Optional.empty());

        // Then
        CustomerUpdateException exception = assertThrows(CustomerUpdateException.class,
                () -> customerService.transfer(amount, senderId, receiverId));
        assertEquals("Customer Receiver Doest Not Exist", exception.getMessage());
    }

    @Test
    void transfer_shouldThrowExceptionIfSenderHasInsufficientBalance() {
        // Given
        long senderId = 1L;
        long receiverId = 2L;
        BigDecimal amount = BigDecimal.valueOf(30);

        Customer sender = new Customer(senderId, "Sender", BigDecimal.TEN);

        // When
        when(customerRepository.findById(senderId)).thenReturn(Optional.of(sender));

        // Then
        CustomerUpdateException exception = assertThrows(CustomerUpdateException.class,
                () -> customerService.transfer(amount, senderId, receiverId));
        assertEquals("Customer Sender Doest Not Have Enough Balance", exception.getMessage());
    }

}

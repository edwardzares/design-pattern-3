package com.example.patterns_banking.services.proxy;

import com.example.patterns_banking.dtos.CustomerDTO;
import com.example.patterns_banking.models.Customer;
import com.example.patterns_banking.repositories.ICustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerOperationsProxy implements ICustomerOperations{
    private final ICustomerRepository customerRepository;

    public CustomerOperationsProxy(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(CustomerDTO customerDTO) {

        if(customerDTO.getEmail().contains("@yahoo")){
            throw new IllegalArgumentException("El correo no puede ser de dominio yahoo");
        }

        Customer customer = Customer
                .builder()
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .build();

        // Implementar proxy para verificar que el correo no sea del dominio yahoo
        return customerRepository.save(customer);
    }
}

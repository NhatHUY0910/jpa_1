package com.practice_jpa_1.service.impl;

import com.practice_jpa_1.model.Customer;
import com.practice_jpa_1.repository.ICustomerRepository;
import com.practice_jpa_1.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}

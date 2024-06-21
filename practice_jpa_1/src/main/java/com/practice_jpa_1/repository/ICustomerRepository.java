package com.practice_jpa_1.repository;

import com.practice_jpa_1.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {
}

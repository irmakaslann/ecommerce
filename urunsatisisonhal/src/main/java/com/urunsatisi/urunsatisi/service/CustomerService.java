package com.urunsatisi.urunsatisi.service;

import com.urunsatisi.urunsatisi.model.Customer;

import java.util.List;

public interface CustomerService {
     Customer getCustomerByName(String name);

     List<Customer> getAllCustomer();

     Customer addCustomer(Customer customer);

     void deleteCustomerById(Long id);

     Customer updateCustomerById(Long id, Customer customer);
}

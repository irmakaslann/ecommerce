package com.urunsatisi.urunsatisi.service;

import com.urunsatisi.urunsatisi.entities.Customer;

import java.util.List;

public interface CustomerService {
    public Customer getCustomerByName(String name);
    public Integer calculateAge(int yearofBirth);
    public List<Customer> getAllCustomer();
    public Customer addCustomer(Customer customer);
    public void deleteCustomerById(Long id);
    public Customer updateCustomerById(Long id, Customer customer);
}

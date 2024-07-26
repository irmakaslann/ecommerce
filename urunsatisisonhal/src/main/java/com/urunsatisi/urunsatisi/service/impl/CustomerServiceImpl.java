package com.urunsatisi.urunsatisi.service.impl;

import com.urunsatisi.urunsatisi.service.CustomerService;
import com.urunsatisi.urunsatisi.model.Customer;
import com.urunsatisi.urunsatisi.config.exception.SourceNotFoundException;
import com.urunsatisi.urunsatisi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public Customer getCustomerByName(String name) {
        return modelMapper.map(customerRepository.findByName(name), Customer.class);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return Collections.singletonList(modelMapper.map(customerRepository.findAll(), Customer.class));
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return modelMapper.map(customerRepository.save(customer), Customer.class);
    }

    @Override
    public void deleteCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new SourceNotFoundException("Customer not found with id : " + id));
        customerRepository.delete(customer);
    }

    @Override
    public Customer updateCustomerById(Long id, Customer customer) {
        Customer idCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new SourceNotFoundException("Customer not found"));
        modelMapper.map(customer, idCustomer);
        Customer updatedCustomer = customerRepository.save(idCustomer);
        return modelMapper.map(updatedCustomer, Customer.class);
    }
}





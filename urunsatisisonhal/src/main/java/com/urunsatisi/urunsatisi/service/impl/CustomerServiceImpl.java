package com.urunsatisi.urunsatisi.service.impl;

import com.urunsatisi.urunsatisi.service.CustomerService;
import com.urunsatisi.urunsatisi.entities.Customer;
import com.urunsatisi.urunsatisi.exception.CustomerNotFoundException;
import com.urunsatisi.urunsatisi.exception.CustomerNotNullException;
import com.urunsatisi.urunsatisi.repository.CustomerRepository;
import lombok.AllArgsConstructor;
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
        Customer customer = customerRepository.findByName(name);
        return modelMapper.map(customer, Customer.class);

    }

    @Override
    public Integer calculateAge(int yearofBirth) {
        return 2024-yearofBirth;
    }

    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> customerAll = customerRepository.findAll();
       return Collections.singletonList(modelMapper.map(customerAll, Customer.class));

    }

    @Override
    public Customer addCustomer(Customer customer) {
        if(customer.getName().isEmpty()|| customer.getName().isBlank()){
            throw new CustomerNotNullException("customer name is empty");
        }

        customer.setName(customer.getName());
        modelMapper.map(customer,customer);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer,Customer.class);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer updateCustomerById(Long id, Customer customer) {
        Customer idCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        modelMapper.map(customer, idCustomer);

        Customer updatedCustomer = customerRepository.save(idCustomer);

        return modelMapper.map(updatedCustomer, Customer.class);
    }
}

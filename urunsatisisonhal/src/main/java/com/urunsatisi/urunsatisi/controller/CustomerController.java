package com.urunsatisi.urunsatisi.controller;

import com.urunsatisi.urunsatisi.service.CustomerService;
import com.urunsatisi.urunsatisi.model.dto.CustomerDto;
import com.urunsatisi.urunsatisi.model.Customer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<CustomerDto> getAllCustomer() {
        List<Customer> customers = customerService.getAllCustomer();

        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());

    }
    @GetMapping("/{name}")
    public CustomerDto getCustomer(@PathVariable("name") String name) {
        Customer customer = customerService.getCustomerByName(name);
        return modelMapper.map(customer, CustomerDto.class);
    }

    @PostMapping("/add")
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.addCustomer(modelMapper.map(customerDto, Customer.class));
        return modelMapper.map(customer, CustomerDto.class);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }

    @PutMapping("/customer/{id}")
    public CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customer.setId(id);
        Customer updatedCustomer = customerService.updateCustomerById(id, customer);
        return modelMapper.map(updatedCustomer, CustomerDto.class);

    }
}


















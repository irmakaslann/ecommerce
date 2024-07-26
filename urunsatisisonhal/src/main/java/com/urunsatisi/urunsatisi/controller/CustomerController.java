package com.urunsatisi.urunsatisi.controller;

import com.urunsatisi.urunsatisi.service.CustomerService;
import com.urunsatisi.urunsatisi.dto.CustomerDto;
import com.urunsatisi.urunsatisi.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<CustomerDto> getAllCustomer() {
        List<Customer> customers=customerService.getAllCustomer();
        return customers.stream()
                .map(customer->modelMapper.map(customer,CustomerDto.class))
                .collect(Collectors.toList());

    }


    @GetMapping("/name")
    public CustomerDto getCustomer(@RequestParam String name) {

        Customer customer = customerService.getCustomerByName(name);


        if (customer == null) {

            throw new IllegalArgumentException("Customer not found with name: " + name);
        }


        return modelMapper.map(customer, CustomerDto.class);
    }

    @GetMapping("/age")
    ResponseEntity<String> getCustomerAge(@RequestParam Integer yearofBirth) {
        int age = customerService.calculateAge(yearofBirth);
        String responseMessage = "Your age is " + age;
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
    @PostMapping("/add")
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.addCustomer(modelMapper.map(customerDto,Customer.class));
        return modelMapper.map(customer,CustomerDto.class);
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

package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.entity.CustomerEntity;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return null;
    }

    @Override
    public List<CustomerSearchResponse> getCustomerByParams(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        List<CustomerEntity> results = customerRepository.findCustomerByParams(customerSearchRequest, pageable);
        return results.stream().map(customerConverter::entityToResponse).collect(Collectors.toList());
    }

    @Override
    public int getCustomerCountByParam(CustomerSearchRequest customerSearchRequest) {
        return customerRepository.getCustomerCount(customerSearchRequest);
    }

    @Override
    public void addOrEditCustomer(CustomerDTO customerDTO) {
        customerRepository.save(customerConverter.dtoToEntity(customerDTO));
    }

    @Override
    public void deleteCustomer(List<Long> ids) {

    }
}

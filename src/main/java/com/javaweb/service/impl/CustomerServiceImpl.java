package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.entity.CustomerEntity;
import com.javaweb.service.CustomerService;
import com.javaweb.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("No customer with id = " + id));
        return customerConverter.entityToDto(customerEntity);
    }

    @Override
    public CustomerDTO getCustomerByIdAndStatusAndAssignedStaff(Long customerId, Integer status ,Long assignedStaffId) {
        CustomerEntity customerEntity = customerRepository.findCustomerByIdAndStatusAndAssignedStaff(customerId, status, assignedStaffId).orElse(null);
        return customerEntity == null ? null : customerConverter.entityToDto(customerEntity);
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
        CustomerEntity customerEntity = customerConverter.dtoToEntity(customerDTO);
        boolean isEditCustomer = !StringUtil.isEmpty(customerDTO.getId());

        if (isEditCustomer) {
            CustomerEntity oldCustomerEntity = customerRepository.findById(customerDTO.getId()).orElseThrow(() -> new RuntimeException("No customer with id = " + customerDTO.getId()));
            customerEntity.setAssignedStaffs(oldCustomerEntity.getAssignedStaffs());
        }

        customerRepository.save(customerEntity);
    }

    @Override
    @Transactional
    public void softDeleteCustomer(List<Long> ids) {
        customerRepository.deactivateCustomers(ids);
    }
}

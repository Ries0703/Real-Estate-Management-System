package com.javaweb.service;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    CustomerDTO getCustomerById(Long id);
    CustomerDTO getCustomerByIdAndStatusAndAssignedStaff(Long id, Integer status ,Long assignedStaffId);
    List<CustomerSearchResponse> getCustomerByParams(CustomerSearchRequest customerSearchRequest, Pageable pageable);
    int getCustomerCountByParam(CustomerSearchRequest customerSearchRequest);
    void addOrEditCustomer(CustomerDTO customerDTO);
    void softDeleteCustomer(List<Long> ids);
}

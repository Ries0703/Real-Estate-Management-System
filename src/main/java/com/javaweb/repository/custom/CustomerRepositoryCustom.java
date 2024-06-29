package com.javaweb.repository.custom;

import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.entity.CustomerEntity;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryCustom {
    List<CustomerEntity> findCustomerByParams(CustomerSearchRequest customerSearchRequest, Pageable pageable);
    Optional<CustomerEntity> findCustomerByIdAndAssignedStaff(Long customerId, Long staffId);
    int getCustomerCount(CustomerSearchRequest customerSearchRequest);
}

package com.javaweb.repository;

import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.repository.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CustomerRepositoryCustom {
}

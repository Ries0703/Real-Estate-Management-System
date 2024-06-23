package com.javaweb.repository;

import com.javaweb.enums.TransactionType;
import com.javaweb.repository.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByCodeAndCustomerEntity_Id(TransactionType code, Long id);
}

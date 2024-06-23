package com.javaweb.service;

import com.javaweb.enums.TransactionType;
import com.javaweb.model.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    void addOrEditTransaction(TransactionDTO transactionDTO);
    List<TransactionDTO> getTransactionsByCodeAndCustomerId(TransactionType transactionType, Long customerId);
}

package com.javaweb.service;

import com.javaweb.model.dto.TransactionDTO;

public interface TransactionService {
    void addTransaction(TransactionDTO transactionDTO);
    void updateTransaction(TransactionDTO transactionDTO);
}

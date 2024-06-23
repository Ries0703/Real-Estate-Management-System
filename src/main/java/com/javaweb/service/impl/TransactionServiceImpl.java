package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.enums.TransactionType;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.entity.CustomerEntity;
import com.javaweb.repository.entity.TransactionEntity;
import com.javaweb.repository.entity.UserEntity;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IUserService;
import com.javaweb.service.TransactionService;
import com.javaweb.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionConverter transactionConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void addOrEditTransaction(TransactionDTO transactionDTO) {


        TransactionEntity transaction;

        if (StringUtil.isEmpty(transactionDTO.getId())) {
            Long staffId = SecurityUtils.getPrincipal().getId();
            UserEntity staff = userRepository.findById(staffId).orElseThrow(() -> new RuntimeException("No staff with id = " + staffId));
            CustomerEntity customerEntity = (customerRepository.findById(transactionDTO.getCustomerId()).orElseThrow(() -> new RuntimeException("No customer with id = " + transactionDTO.getCustomerId())));
            transaction = transactionConverter.dtoToEntity(transactionDTO);
            transaction.setUserEntity(staff);
            transaction.setCustomerEntity(customerEntity);
        } else {
            transaction = transactionRepository.findById(transactionDTO.getId()).orElseThrow(() -> new RuntimeException("No such user"));
            transaction.setNote(transactionDTO.getNote());
        }


        transactionRepository.save(transaction);




    }

    @Override
    public List<TransactionDTO> getTransactionsByCodeAndCustomerId(TransactionType transactionType, Long customerId) {
        List<TransactionEntity> transactionEntity = transactionRepository.findByCodeAndCustomerEntity_Id(transactionType, customerId);
        return transactionEntity.stream().map(transactionConverter::entityToDTO).collect(Collectors.toList());
    }
}

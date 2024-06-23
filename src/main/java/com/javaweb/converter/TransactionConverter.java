package com.javaweb.converter;

import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.entity.TransactionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    public TransactionDTO entityToDTO(TransactionEntity transaction) {
        return modelMapper.map(transaction, TransactionDTO.class);
    }

    public TransactionEntity dtoToEntity(TransactionDTO dto) {
        return modelMapper.map(dto, TransactionEntity.class);
    }
}

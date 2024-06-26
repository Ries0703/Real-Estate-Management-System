package com.javaweb.converter;

import com.javaweb.enums.Status;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    @Autowired
    ModelMapper modelMapper;

    public CustomerEntity dtoToEntity(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        customerEntity.setStatus(customerDTO.getStatus().getStatusName());
        return customerEntity;
    }

    public CustomerDTO entityToDto(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        customerDTO.setStatus(Status.reverseLookup(customerEntity.getStatus()).orElseThrow(() -> new RuntimeException("No such enum for Status")));
        return customerDTO;
    }

    public CustomerSearchResponse entityToResponse(CustomerEntity customerEntity) {
        CustomerSearchResponse customerSearchResponse = modelMapper.map(customerEntity, CustomerSearchResponse.class);
        customerSearchResponse.setStatus(Status.reverseLookup(customerEntity.getStatus()).orElseThrow(() -> new RuntimeException("No such enum for Status")));
        return customerSearchResponse;
    }
}

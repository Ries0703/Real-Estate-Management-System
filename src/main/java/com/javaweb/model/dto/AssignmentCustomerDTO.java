package com.javaweb.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class AssignmentCustomerDTO {
    private Long customerId;
    private List<Long> staffIds;
}

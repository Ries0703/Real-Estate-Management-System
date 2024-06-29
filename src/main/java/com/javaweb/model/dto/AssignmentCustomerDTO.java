package com.javaweb.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class AssignmentCustomerDTO {
    @NotBlank(message = "must have customer id to assign staff to customer")
    private Long customerId;

    private List<Long> staffIds;
}

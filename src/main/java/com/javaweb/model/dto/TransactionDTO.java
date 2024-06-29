package com.javaweb.model.dto;

import com.javaweb.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class TransactionDTO extends AbstractDTO<TransactionDTO> {

    @NotEmpty(message = "Must have code for transaction")
    private TransactionType code;
    private String note;
    @NotEmpty(message = "Must have customer id for transaction")
    private Long customerId;
}

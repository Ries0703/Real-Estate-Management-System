package com.javaweb.model.dto;

import com.javaweb.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransactionDTO extends AbstractDTO<TransactionDTO> {
    private TransactionType code;
    private String note;
    private Long customerId;
}

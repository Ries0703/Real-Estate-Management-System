package com.javaweb.model.dto;


import com.javaweb.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDTO extends AbstractDTO<CustomerDTO> {
    private String fullName;
    private String phone;
    private String email;
    private String companyName;
    private String demand;
    private Status status = Status.CHUA_XU_LY;
    private List<TransactionDTO> transactionDTOList;
}

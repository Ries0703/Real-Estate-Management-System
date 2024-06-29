package com.javaweb.model.dto;


import com.javaweb.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CustomerDTO extends AbstractDTO<CustomerDTO> {
    @NotBlank(message = "Must enter full name")
    private String fullName;

    @NotBlank(message = "Must enter phone")
    private String phone;

    private String email;
    private String companyName;
    private String demand;
    private Status status = Status.CHUA_XU_LY;
}

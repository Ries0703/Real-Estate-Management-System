package com.javaweb.model.request;

import com.javaweb.enums.Status;
import com.javaweb.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSearchRequest extends AbstractDTO<CustomerSearchRequest> {
    private String fullName;
    private String phone;
    private String email;
    private Status status;
    private Long staffId;
}

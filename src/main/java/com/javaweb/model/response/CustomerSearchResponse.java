package com.javaweb.model.response;

import com.javaweb.enums.Status;
import com.javaweb.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSearchResponse extends AbstractDTO<CustomerSearchResponse> {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String demand;
    private Status status;
}

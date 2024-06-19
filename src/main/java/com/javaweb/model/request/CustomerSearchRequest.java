package com.javaweb.model.request;

import lombok.Data;

@Data
public class CustomerSearchRequest {
    private String name;
    private String phone;
    private String email;
    private Long staffId;
}

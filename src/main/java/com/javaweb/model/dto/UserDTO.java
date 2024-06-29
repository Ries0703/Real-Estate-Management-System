package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class UserDTO extends AbstractDTO<UserDTO> {
    @NotEmpty(message = "username must not be blank")
    private String userName;

    @NotEmpty(message = "full name must not be blank")
    private String fullName;

    @NotEmpty(message = "password must not be blank")
    private String password;
    private Integer status;
    private List<RoleDTO> roles = new ArrayList<>();
    private String roleName;
    private String roleCode;
    private Map<String,String> roleDTOs = new HashMap<>();

}

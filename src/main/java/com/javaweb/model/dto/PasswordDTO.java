package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Setter
@Getter
public class PasswordDTO implements Serializable {

    private static final long serialVersionUID = 8835146939192307340L;

    @NotEmpty(message = "must enter old password")
    private String oldPassword;

    @NotEmpty(message = "must enter new password")
    private String newPassword;

    @NotEmpty(message = "must re-enter old password")
    private String confirmPassword;

}

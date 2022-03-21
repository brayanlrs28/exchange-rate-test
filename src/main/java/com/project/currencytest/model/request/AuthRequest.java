package com.project.currencytest.model.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Class AuthRequest.
 * @author lr
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotEmpty(message = "'userName' or 'password'  No debe ser vacio!")
    @Size(min = 3, max = 50,message = "'userName' or 'password' debe tener 50 caracteres")
    private String userName;

    @NotEmpty(message = "'userName' or 'password'  No debe ser vacio!")
    @Size(min = 3, max = 50,message = "'userName' or 'password' debe tener 50 caracteres")
    private String password;
}
package com.project.currencytest.model.response;

import lombok.*;

/**
 * Class AuthResponse.
 * @author lr
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private boolean userValid;
}
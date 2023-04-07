package com.ecommerce.ecommerce.registration;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}

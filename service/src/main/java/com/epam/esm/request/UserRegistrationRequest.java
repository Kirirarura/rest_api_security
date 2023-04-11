package com.epam.esm.request;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserRegistrationRequest {

    String username;
    String password;
    String email;
    String firstname;
    String lastname;

}

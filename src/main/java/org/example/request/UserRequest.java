package org.example.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Long phone;
    private String role;
}

package org.example.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindByEmailRequest {
    private String email;
}

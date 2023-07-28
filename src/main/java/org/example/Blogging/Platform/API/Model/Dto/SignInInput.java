package org.example.Blogging.Platform.API.Model.Dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInInput {

    @Email(message = "Please enter a valid email")
    private String email;
    private String password;
}

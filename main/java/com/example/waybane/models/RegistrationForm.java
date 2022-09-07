package com.example.waybane.models;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Objects;

@Data
public class RegistrationForm {

    @Pattern(regexp = "^[a-z0-9_-]{3,16}$",
            message = "Username must contain digits and characters (3-16 chars)")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,20}$",
            message = "Password must have digits, upper and lower case characters (8-20 chars)")
    private String password;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,20}$",
            message = "Password must have digits, upper and lower case characters (8-20 chars)")
    private String confirm;

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        return user;
    }

    public boolean matchPasswords() {
        return Objects.equals(password, confirm);
    }

}

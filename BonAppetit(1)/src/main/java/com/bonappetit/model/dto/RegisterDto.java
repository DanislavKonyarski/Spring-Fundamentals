package com.bonappetit.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterDto {

    @NotBlank
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    public String username;
    @NotBlank
    @Email
    public String email;

    @NotBlank
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    public String password;
    @NotBlank
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    public String confirmPassword;

    public RegisterDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

package com.cardone.checkpassword.dto;

public class PasswordDTO {
    private String password;

    public PasswordDTO() {
    }

    public PasswordDTO(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}

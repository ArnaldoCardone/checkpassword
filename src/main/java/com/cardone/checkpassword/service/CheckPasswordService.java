package com.cardone.checkpassword.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cardone.checkpassword.dto.PasswordDTO;

import reactor.core.publisher.Mono;

@Service
public class CheckPasswordService {

    private List<String> errors;

    public Mono<List<String>> checkPassword(PasswordDTO password) {
        errors = new ArrayList<>();

        return Mono.just(password.getPassword())
                .map(pw -> {
                    validateLength(pw);
                    validateUpperCase(pw);
                    validateLowerCase(pw);
                    validateNumeric(pw);
                    validateSpecialCharacter(pw);

                    return errors;
                });
    }

    private void validateLength(String password) {
        if (password == null || password.isEmpty() || password.length() < 8) {
            errors.add("Password does not contain at least 8 characters");
        }
    }

    private void validateLowerCase(String password) {
        if (password.chars().filter(Character::isLowerCase).findAny().isEmpty()) {
            errors.add("Password does not contain lowercase letters");
        }
    }

    private void validateUpperCase(String password) {
        if (password.chars().filter(Character::isUpperCase).findAny().isEmpty()) {
            errors.add("Password does not contain uppercase letters");
        }
    }

    private void validateNumeric(String password) {
        if (!password.matches(".*[0-9].*")) {
            errors.add("Password does not contain numbers");
        }
    }
    private void validateSpecialCharacter(String password) {
        if (!password.matches(".*[!@#$%^&*()-+].*")) {
            errors.add("Password does not contain special characters");
        }
    }
}

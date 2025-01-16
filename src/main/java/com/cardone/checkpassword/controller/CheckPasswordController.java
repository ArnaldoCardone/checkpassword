package com.cardone.checkpassword.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardone.checkpassword.dto.PasswordDTO;
import com.cardone.checkpassword.service.CheckPasswordService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/validate-password")
public class CheckPasswordController {
    
    @Autowired
    private CheckPasswordService checkPasswordService;

    @PostMapping
    public Mono<ResponseEntity<?>> checkPassword(@RequestBody PasswordDTO password) {
        return checkPasswordService.checkPassword(password).map(errors -> {
            if (errors.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        });
    }
}

package com.example.Eticaret;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEndocer {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password";
        String encodePassword = encoder.encode(rawPassword);

        System.out.println(encodePassword);
    }
}

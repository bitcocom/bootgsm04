package com.example.bootgsm04;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderExample {

    public static void main(String[] args) {
        // Create an instance of BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Password to be hashed
        String password = "12345";

        if (password != null && !password.isEmpty()) {
            // Hash the password
            String hashedPassword = passwordEncoder.encode(password);

            // Print the hashed password
            System.out.println("Original Password: " + password);
            System.out.println("Hashed Password: " + hashedPassword);
        } else {
            System.out.println("Password is null or empty.");
        }
    }
}

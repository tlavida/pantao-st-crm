package com.pantao_st_crm.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordLogger {

    private static final Logger logger = LoggerFactory.getLogger(PasswordLogger.class);

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordLogger(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void logEncodedPasswords() {
        String password = "1234";
        String encryptedAdminPassword = passwordEncoder.encode(password);

        logger.info("Зашифрованный пароль: {}", encryptedAdminPassword);
    }
}

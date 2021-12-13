package com.internetProvider.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptoUtilTest {

    @Test
    public void testPasswordEncryption() {
        String password = CryptoUtil.getEncryptedPassword("taras");
        String encryptedPassword = "BB1AE400748F272737C29D3CF8DB5F12";
        assertEquals(password, encryptedPassword);
    }
}
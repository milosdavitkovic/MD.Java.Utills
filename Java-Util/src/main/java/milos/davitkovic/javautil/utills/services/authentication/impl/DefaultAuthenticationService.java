package milos.davitkovic.javautil.utills.services.authentication.impl;

import milos.davitkovic.javautil.utills.services.authentication.AuthenticationService;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@Service
public class DefaultAuthenticationService implements AuthenticationService
{
    @Override
    public KeyPair getNewKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate key pair", e);
        }
    }
}

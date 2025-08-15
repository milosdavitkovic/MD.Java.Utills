package milos.davitkovic.javautil.utills.services.authentication;

import java.security.KeyPair;

/**
 * Service interface for authentication and cryptographic key management operations.
 * 
 * <p>This interface defines the contract for cryptographic operations including
 * key pair generation. It provides the core functionality for secure authentication
 * and encryption services.</p>
 * 
 * @author Milos Davitkovic
 * @version 1.0
 * @since 1.0
 */
public interface AuthenticationService {
    /**
     * Generates a new cryptographic key pair.
     * 
     * <p>This method creates a new RSA key pair with a key size of 2048 bits.
     * The generated key pair can be used for encryption, decryption, and
     * digital signature operations.</p>
     * 
     * @return a new KeyPair containing the public and private keys
     */
    KeyPair getNewKeyPair();
}

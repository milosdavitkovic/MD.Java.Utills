package milos.davitkovic.javautil.utills.facade.authentication;

import milos.davitkovic.javautil.utills.facade.dto.AuthenticationDTO;

/**
 * Facade interface for authentication and cryptographic operations.
 * 
 * <p>This interface provides high-level methods for managing cryptographic key pairs
 * and authentication-related operations. It abstracts the complexity of cryptographic
 * operations and provides a simple interface for key generation and management.</p>
 * 
 * @author Milos Davitkovic
 * @version 1.0
 * @since 1.0
 */
public interface AuthenticationFacade
{
   /**
    * Generates a new cryptographic key pair for authentication purposes.
    * 
    * <p>This method creates a new RSA key pair suitable for secure communication
    * and authentication. The generated keys are returned in a DTO format containing
    * both the public and private key components.</p>
    * 
    * @return an AuthenticationDTO containing the generated public and private keys
    */
   AuthenticationDTO getNewKeyPair();
}

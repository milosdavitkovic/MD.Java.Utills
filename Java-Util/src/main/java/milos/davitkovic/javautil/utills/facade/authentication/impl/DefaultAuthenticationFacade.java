package milos.davitkovic.javautil.utills.facade.authentication.impl;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.javautil.utills.annotations.Facade;
import milos.davitkovic.javautil.utills.facade.authentication.AuthenticationFacade;
import milos.davitkovic.javautil.utills.facade.dto.AuthenticationDTO;
import milos.davitkovic.javautil.utills.services.authentication.AuthenticationService;
import java.security.KeyPair;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Facade
public class DefaultAuthenticationFacade implements AuthenticationFacade
{
   @Autowired
   private AuthenticationService authenticationService;

   @Override
   public AuthenticationDTO getNewKeyPair()
   {
      final KeyPair newKeyPair = authenticationService.getNewKeyPair();
      log.info("New Key Pair via RSA is created.");
      return convert(newKeyPair);
   }

   private AuthenticationDTO convert(final KeyPair newKeyPair)
   {
      final AuthenticationDTO authenticationDTO = new AuthenticationDTO();

      final byte[] privateKeyBytes = newKeyPair.getPrivate().getEncoded();
      final String privateKey = Base64.getEncoder().encodeToString(privateKeyBytes);
      authenticationDTO.setPrivateKey(privateKey);

      final byte[] publicKeyBytes = newKeyPair.getPublic().getEncoded();
      final String publicKey = Base64.getEncoder().encodeToString(publicKeyBytes);
      authenticationDTO.setPublicKey(publicKey);

      log.info("New Private key is {} and New Public key is {}.", privateKey, publicKey);
      return authenticationDTO;
   }
}

package milos.davitkovic.utills.facade.authentication.impl;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.utills.annotations.Facade;
import milos.davitkovic.utills.facade.authentication.AuthenticationFacade;
import milos.davitkovic.utills.facade.dto.AuthenticationDTO;
import milos.davitkovic.utills.facade.dto.UserDTO;
import milos.davitkovic.utills.services.authentication.AuthenticationService;
import milos.davitkovic.utills.services.crypto.TweetNaclFast;
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
      final TweetNaclFast.Box.KeyPair newKeyPair = authenticationService.getNewKeyPair();
      log.info("New Key Pair via TweetNaclFast is created.");
      return convert(newKeyPair);
   }

   private AuthenticationDTO convert(final TweetNaclFast.Box.KeyPair newKeyPair)
   {
      final AuthenticationDTO authenticationDTO = new AuthenticationDTO();

      final byte[] secretKey = newKeyPair.getSecretKey();
      final String opoPrivateKey = TweetNaclFast.hexEncodeToString(secretKey);
      authenticationDTO.setPrivateKey(opoPrivateKey);

      final byte[] publicKey = newKeyPair.getPublicKey();
      final String opoPublicKey = TweetNaclFast.hexEncodeToString(publicKey);
      authenticationDTO.setPublicKey(opoPublicKey);

      log.info("New Private key is {} and New Public key is {}.", opoPrivateKey, opoPublicKey);
      return authenticationDTO;
   }

   @Override
   public boolean isAuthorized(String userEmail) {
      return false;
   }
}

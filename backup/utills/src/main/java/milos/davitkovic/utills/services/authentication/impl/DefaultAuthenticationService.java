package milos.davitkovic.utills.services.authentication.impl;

import milos.davitkovic.utills.services.authentication.AuthenticationService;
import milos.davitkovic.utills.services.crypto.TweetNaclFast;
import org.springframework.stereotype.Service;

@Service
public class DefaultAuthenticationService implements AuthenticationService
{
   @Override
   public TweetNaclFast.Box.KeyPair getNewKeyPair()
   {
      return TweetNaclFast.Box.keyPair();
   }
}

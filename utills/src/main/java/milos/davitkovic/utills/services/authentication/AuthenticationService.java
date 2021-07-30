package milos.davitkovic.utills.services.authentication;

import milos.davitkovic.utills.annotations.Interface;
import milos.davitkovic.utills.services.crypto.TweetNaclFast;

@Interface
public interface AuthenticationService
{
   TweetNaclFast.Box.KeyPair getNewKeyPair();
}

package milos.davitkovic.utills.facade.authentication;

import milos.davitkovic.utills.annotations.Interface;
import milos.davitkovic.utills.facade.dto.AuthenticationDTO;
import milos.davitkovic.utills.facade.dto.UserDTO;


@Interface
public interface AuthenticationFacade
{
   AuthenticationDTO getNewKeyPair();

   boolean isAuthorized(String userEmail);
}

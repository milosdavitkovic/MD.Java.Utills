package milos.davitkovic.utills.facade.authentication;

import milos.davitkovic.utills.annotations.Interface;
import milos.davitkovic.utills.facade.dto.AuthenticationDTO;


@Interface
public interface AuthenticationFacade
{
   AuthenticationDTO getNewKeyPair();
}

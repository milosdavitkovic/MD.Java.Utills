package milos.davitkovic.javautil.utills.facade.authentication;

import milos.davitkovic.javautil.utills.annotations.Interface;
import milos.davitkovic.javautil.utills.facade.dto.AuthenticationDTO;



public interface AuthenticationFacade
{
   AuthenticationDTO getNewKeyPair();
}

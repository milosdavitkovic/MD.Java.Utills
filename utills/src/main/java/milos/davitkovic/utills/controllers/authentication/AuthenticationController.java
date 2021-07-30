package milos.davitkovic.utills.controllers.authentication;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.utills.facade.authentication.AuthenticationFacade;
import milos.davitkovic.utills.facade.dto.AuthenticationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "authentication/")
public class AuthenticationController
{
   @Autowired
   private AuthenticationFacade authenticationFacade;

   @GetMapping(value = "/getNewKeyPair")
   @ResponseStatus(value = HttpStatus.OK)
   @ResponseBody
   public AuthenticationDTO productCodesComparing()
   {
      return authenticationFacade.getNewKeyPair();
   }
}

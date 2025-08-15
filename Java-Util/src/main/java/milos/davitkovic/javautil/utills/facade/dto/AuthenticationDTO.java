package milos.davitkovic.javautil.utills.facade.dto;

import lombok.Data;

@Data
public class AuthenticationDTO
{
   private String privateKey;
   private String publicKey;
}

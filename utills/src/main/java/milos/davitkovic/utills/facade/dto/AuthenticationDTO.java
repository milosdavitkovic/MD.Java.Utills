package milos.davitkovic.utills.facade.dto;

import lombok.Data;

@Data
public class AuthenticationDTO
{
   private String privateKey;
   private String publicKey;
}

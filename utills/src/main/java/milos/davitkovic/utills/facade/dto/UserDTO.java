package milos.davitkovic.utills.facade.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String password;
    private String token;
}

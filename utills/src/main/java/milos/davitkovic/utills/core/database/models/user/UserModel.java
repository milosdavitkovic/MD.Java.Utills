package milos.davitkovic.utills.core.database.models.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

//@Entity
@Data
public class UserModel implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private String email;

    private String username;

    private String password;

    private String passwordConfirm;

    private String name;

    private String surname;

    private String gender;

    private String birthday;
}

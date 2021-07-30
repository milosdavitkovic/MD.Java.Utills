package milos.davitkovic.utills.core.database.models.photo;

import lombok.Data;
import milos.davitkovic.utills.core.database.models.user.UserModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Data
public class PhotoModel implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private String name;
    private String url;

    @OneToOne
    private UserModel owner;
}

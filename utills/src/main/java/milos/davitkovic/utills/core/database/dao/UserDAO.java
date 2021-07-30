package milos.davitkovic.utills.core.database.dao;

import milos.davitkovic.utills.annotations.Interface;
import milos.davitkovic.utills.core.database.models.user.UserModel;

import java.util.Optional;

@Interface
public interface UserDAO extends AbstractDAO<UserModel> {

    Optional<UserModel> findByEmail(String email);
}

package milos.davitkovic.utills.core.database.dao;

import milos.davitkovic.utills.annotations.Interface;
import milos.davitkovic.utills.core.database.models.photo.PhotoModel;

import java.util.Optional;

@Interface
public interface PhotoDAO extends AbstractDAO<PhotoModel> {

    Optional<PhotoModel> findByName(String name);
}

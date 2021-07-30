package milos.davitkovic.utills.facade.user;

import milos.davitkovic.utills.annotations.Facade;

@Facade
public interface UserFacade {

    /**
     * Get {@link milos.davitkovic.utills.core.database.models.photo.PhotoModel}'s URL
     * for specified {@link milos.davitkovic.utills.core.database.models.user.UserModel}'s email address.
     * @param userEmail {@link milos.davitkovic.utills.core.database.models.user.UserModel}'s email address
     * @param photoName {@link milos.davitkovic.utills.core.database.models.photo.PhotoModel}'s name
     * @return
     */
    String getUserPhotoPath(String userEmail, String photoName);
}

package milos.davitkovic.utills.facade.user.impl;

import milos.davitkovic.utills.annotations.Facade;
import milos.davitkovic.utills.facade.user.UserFacade;
import milos.davitkovic.utills.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@Facade
public class DefaultUserFacade implements UserFacade {

    @Autowired
    private UserService userService;

    @Override
    public String getUserPhotoPath(final String userEmail, final String photoName) {
        return userService.getUserPhotoPath(userEmail, photoName);
    }
}

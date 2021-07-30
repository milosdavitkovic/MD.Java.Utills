package milos.davitkovic.utills.services.user.impl;

import milos.davitkovic.utills.core.database.models.photo.PhotoModel;
import milos.davitkovic.utills.core.database.models.user.UserModel;
import milos.davitkovic.utills.services.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;

@Service
public class DefaultUserService implements UserService {

////    @Autowired
//    private UserDAO userDAO;
//
////    @Autowired
//    private PhotoDAO photoDAO;

    @Override
    public String getUserPhotoPath(String userEmail, String photoName) {
        assertNotNull("User email cannot be null", userEmail);
        assertNotNull("User photo cannot be null", photoName);

//        final Optional<UserModel> optionalUser = userDAO.findByEmail(userEmail);
//        if (!optionalUser.isPresent()) {
//            throw new UsernameNotFoundException("User not found!");
//        }
//
//        final Optional<PhotoModel> optionalPhoto = photoDAO.findByName(photoName);
//        if (!optionalPhoto.isPresent()) {
//            throw new ResourceNotFoundException("Photo not found!");
//        }
//
//        final PhotoModel photoModel = optionalPhoto.get();
//        final UserModel photoOwner = photoModel.getOwner();
//        if (photoOwner == null) {
//            throw new AuthorizationServiceException("No info, if User is allowed to get the required resource!");
//        }
//
//        if (!StringUtils.equals(userEmail, photoOwner.getEmail())) {
//            throw new AuthorizationServiceException("User is not allowed to get the required resource!");
//        }
//
//        return photoModel.getUrl();

        return null;
    }
}

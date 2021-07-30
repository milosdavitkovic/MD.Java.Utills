package milos.davitkovic.utills.controllers;

import milos.davitkovic.utills.facade.user.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user/")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    /**
     * Get Photo's URL
     * for specified User's email address.
     *
     * @param userEmail User's email address
     * @param photoName Photo's name
     * @return
     */
    @GetMapping(value = "{user_email}/getPhotoPath/{photo_name}")
    @ResponseBody
    public String getPhotoPath(@PathVariable("user_email") String userEmail, @PathVariable("photo_name") String photoName) {
        return userFacade.getUserPhotoPath(userEmail, photoName);
    }
}

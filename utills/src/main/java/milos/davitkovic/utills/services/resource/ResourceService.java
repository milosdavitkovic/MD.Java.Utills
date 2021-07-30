package milos.davitkovic.utills.services.resource;

import milos.davitkovic.utills.annotations.Interface;
import org.springframework.stereotype.Service;

@Interface
public interface ResourceService {

    String getPhoto(String name);
}

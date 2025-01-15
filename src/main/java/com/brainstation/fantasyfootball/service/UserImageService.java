package com.brainstation.fantasyfootball.service;

import com.brainstation.fantasyfootball.model.entity.UserImage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserImageService {
    void saveImage(UserImage image);
    Optional<UserImage> getImage(String username);
}

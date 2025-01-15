package com.brainstation.fantasyfootball.service.impl;

import com.brainstation.fantasyfootball.model.entity.UserImage;
import com.brainstation.fantasyfootball.repository.UserImageRepository;
import com.brainstation.fantasyfootball.service.UserImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserImageServiceImpl implements UserImageService {
    @Autowired
    private UserImageRepository userImageRepository;
    @Override
    public void saveImage(UserImage image) {
        Optional<UserImage> byUsername = getImage(image.getUsername());
        if(byUsername.isPresent()){
            image.setId(byUsername.get().getId());
        }
        userImageRepository.save(image);
    }

    @Override
    public Optional<UserImage> getImage(String username) {
        Optional<UserImage> image = Optional.ofNullable(userImageRepository.findByUsername(username));
        return image;
    }
}

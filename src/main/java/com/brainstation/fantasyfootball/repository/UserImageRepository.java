package com.brainstation.fantasyfootball.repository;

import com.brainstation.fantasyfootball.model.entity.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserImageRepository extends JpaRepository<UserImage, Long> {
     UserImage findByUsername(String username);
}

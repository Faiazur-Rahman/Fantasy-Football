package com.brainstation.fantasyfootball.repository;

import com.brainstation.fantasyfootball.model.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<OTP, Integer> {
}

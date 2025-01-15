package com.brainstation.fantasyfootball.repository;

import com.brainstation.fantasyfootball.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}

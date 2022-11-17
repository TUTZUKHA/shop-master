package com.example.demo.repo;

import com.example.demo.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepo extends JpaRepository<Profile,Integer> {
    Optional<Profile>findByEmailAndDeletedAtIsNull(String email);
    Profile findByEmail(String email);
}

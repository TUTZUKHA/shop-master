package com.example.demo.securitty;

import com.example.demo.entity.Profile;
import com.example.demo.repo.ProfileRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {
    private final ProfileRepo profileRepo;

    public CustomUserDetailsService(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Keldi: loadUserByUsername");
        Optional<Profile> optional =this.profileRepo.findByEmailAndDeletedAtIsNull(email);
        optional.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));

        Profile profile =optional.get();
        System.out.println(profile);

        return new CustomUserDetails(profile);
    }
}

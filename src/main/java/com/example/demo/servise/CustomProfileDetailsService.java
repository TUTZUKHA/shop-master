package com.example.demo.servise;

import com.example.demo.entity.Profile;
import com.example.demo.repo.ProfileRepo;
import com.example.demo.securitty.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class CustomProfileDetailsService implements UserDetailsService {
    private final ProfileRepo profileRepo;

    public CustomProfileDetailsService(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String userame) throws UsernameNotFoundException {
        System.out.println("Keldi: loadUserByUsername");
        Optional<Profile> optional = Optional.ofNullable(this.profileRepo.findByEmail(userame));
        optional.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));

        Profile profile =optional.get();
        System.out.println(profile);

        return new CustomUserDetails(profile);
    }

}

package com.example.demo.servise;

import com.example.demo.entity.Profile;
import com.example.demo.entity.create.CreateProfile;
import com.example.demo.excaption.BadRequest;
import com.example.demo.repo.ProfileRepo;
import com.example.demo.type.ProfileRole;
import com.example.demo.type.ProfileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ProfilrService {
    @Autowired private JwtTokenFilter jwtToken;
    private final ProfileRepo profileRepo;
    @Autowired private MailSenderService mailSender;
    //private final PasswordEncoder passwordEncoder;//todo encoder

    @Value("${serverAddress}")
    private String serverAddres;

    public ProfilrService(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    public String createProfile(CreateProfile createProfile) {
        Profile profile = profileRepo.findByEmail(createProfile.getEmail());
        if (profile != null){
            return "This email already exists!";
        }
        profile = new Profile();
        profile.setName(createProfile.getName());
        profile.setContact(createProfile.getContact());
        profile.setEmail(createProfile.getEmail());
       // profile.setPassword(passwordEncoder.encode(createProfile.getPassword()));//todo password encoder
        profile.setPassword(createProfile.getPassword());
        profile.setCreateAt(LocalDateTime.now());
        profile.setProfileStatus(ProfileStatus.INACTIVE);
        profile.setProfileRole(ProfileRole.USER);
        profileRepo.save(profile);
        profile = profileRepo.findByEmail(profile.getEmail());
        if (mailSender.send(profile)){return "Please confirm your email!";}
        else throw new BadRequest("Email not delivered!");
    }

    public String verification(String token) {
        Profile profile = profileRepo.getById(jwtToken.getUserID(token));
        if (profile == null){
            return "profile not found";
        }
        if (profile.getProfileStatus().equals(ProfileStatus.BLOCKED)){
            return "Your profile is blocked";
        }
        if (jwtToken.getExpirationDate(token).after(new Date(System.currentTimeMillis()))){
            profile.setProfileStatus(ProfileStatus.ACTIVE);
            profileRepo.save(profile);
            return "Successful verified";
        }
       return "please return verified";
    }
}

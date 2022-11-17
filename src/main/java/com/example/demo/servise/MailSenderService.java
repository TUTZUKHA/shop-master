package com.example.demo.servise;

import com.example.demo.entity.Profile;
import com.example.demo.excaption.BadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MailSenderService {
    private final JwtTokenFilter jwtToken;
    @Value("${serverAddress}")
    private String serverAddress;
    @Autowired
    private MailSender mailSender;

    public MailSenderService(JwtTokenFilter jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Boolean send(Profile profile){
      try {
          String token = jwtToken.createToken(profile.getId(),profile.getEmail());
          String link = String.format("%sapi/v1/profile/verification/%s",serverAddress,token);
          String content = "Click for verification this link: "+ link+" \n"+ LocalDateTime.now();
          SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
          simpleMailMessage.setTo(profile.getEmail());
          simpleMailMessage.setSubject("verification");
          simpleMailMessage.setText(content);
          mailSender.send(simpleMailMessage);
          return true;
      }catch (RuntimeException e){
          throw new BadRequest(e.getMessage());
      }
    }
}

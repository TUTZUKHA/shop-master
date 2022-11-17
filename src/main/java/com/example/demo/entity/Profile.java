package com.example.demo.entity;

import com.example.demo.type.ProfileRole;
import com.example.demo.type.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("profils"))
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String contact;
    private String password;
    private LocalDateTime createAt;
    private ProfileStatus profileStatus;
    private ProfileRole profileRole; //todo: role
    private Integer imageId;
    private LocalDateTime deletedAt;
}

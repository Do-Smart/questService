package com.dosmart.questService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDetails {
    private String profilePicture;
    private String email;
    private String firstName;
    private String lastName;
    private String organization;
    private String phoneNumber;
    private String password;
    private String organizationalAddress;
    private boolean authority;
}

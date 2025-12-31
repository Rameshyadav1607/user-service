package com.sriram.ecommerce.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDomain {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String password;
    private String emailId;
    private String phoneNumner;



}

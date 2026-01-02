package com.sriram.ecommerce.user.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserAddresResponse {

    private Integer userId;
    private String firstName;
    private String lastName;
    private List<UserAddressDomain> userAddressDomainList;
}

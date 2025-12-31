package com.sriram.ecommerce.user.domain;

import com.sriram.ecommerce.user.valid.UserAddressValid;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@UserAddressValid
public class UserAddressDomain {

    private Integer addressId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private boolean isDefault;

}

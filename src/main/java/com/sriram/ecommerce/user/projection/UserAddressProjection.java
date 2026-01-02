package com.sriram.ecommerce.user.projection;

public interface UserAddressProjection {
    Integer getUserId();
    Integer getAddressId();
    String getFirstName();
    String getLastName();
    String getAddressLine1();
    String getAddressLine2();
    String getCity();
    String getState();
    String getCountry();
    String getPincode();
    Boolean getIsDefault();
}

package com.sriram.ecommerce.user.resource;

import com.sriram.ecommerce.user.domain.UserAddressDomain;
import com.sriram.ecommerce.user.service.UserAddresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-address")
public class UserAddressResource {
    @Autowired
    private UserAddresService userAddresService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> createAddress(@PathVariable Integer userId,@Validated @RequestBody UserAddressDomain userAddressDomain) {
        return userAddresService.createAddress(userId, userAddressDomain);
    }

    @PutMapping
    public ResponseEntity<UserAddressDomain> updateUserAddress(@Validated @RequestBody UserAddressDomain userAddressDomain) {
        return userAddresService.updateUserAddress(userAddressDomain);
    }

    @GetMapping("/{addressId}")
    public UserAddressDomain fetchUserAddress(@PathVariable Integer addressId) {
        return userAddresService.fetchUserAddress(addressId);
    }
    @PutMapping("/set-default-address/{userId}/{addressId}")
    public String setDefaultAddress(@PathVariable Integer userId,@PathVariable Integer addressId ){
        return userAddresService.setDefaultAddress(userId,addressId);
    }


}

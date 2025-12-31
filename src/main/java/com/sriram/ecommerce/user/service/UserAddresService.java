package com.sriram.ecommerce.user.service;

import com.sriram.ecommerce.user.domain.UserAddressDomain;
import com.sriram.ecommerce.user.exception.ResourceNotFoundException;
import com.sriram.ecommerce.user.model.UserAddress;
import com.sriram.ecommerce.user.model.Users;
import com.sriram.ecommerce.user.repository.UserAddressRepository;
import com.sriram.ecommerce.user.repository.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class UserAddresService {
    @Autowired
    private UserAddressRepository userAddressRepository;
    @Autowired
    private UsersRepository usersRepository;

    public ResponseEntity<?> createAddress(Integer userId, UserAddressDomain userAddressDomain) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressDomain, userAddress, "addressId");
        Users users = usersRepository.findByUserId(userId)
                .orElseThrow(()->new ResourceNotFoundException("USER NOT FOUND  :"+userId));
        userAddress.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        userAddress.setUsers(users);
        userAddressRepository.save(userAddress);
        return new ResponseEntity<>("user address saved ", HttpStatus.OK);
    }

    public ResponseEntity<UserAddressDomain> updateUserAddress(UserAddressDomain userAddressDomain) {
        UserAddress address = userAddressRepository.findByAddressId(userAddressDomain.getAddressId())
                .orElseThrow(()->new ResourceNotFoundException("addressId is not found :" +userAddressDomain.getAddressId()));
        BeanUtils.copyProperties(userAddressDomain, address, "addressId");
        address.setUpdatedDate(ZonedDateTime.now());
        UserAddress saved = userAddressRepository.save(address);
        UserAddressDomain response = new UserAddressDomain();
        BeanUtils.copyProperties(saved, response);
        return ResponseEntity.ok(response);
    }

    public UserAddressDomain fetchUserAddress(Integer addressId) {
        UserAddress address = userAddressRepository.findByAddressId(addressId)
                .orElseThrow(()->new ResourceNotFoundException("addressId not found :"+addressId));
        UserAddressDomain resposne = new UserAddressDomain();
        BeanUtils.copyProperties(address, resposne);
        return resposne;
    }

    public String setDefaultAddress(Integer userId, Integer addressId) {
        UserAddress address = userAddressRepository.findByAddressId(addressId)
                .orElseThrow(()->new ResourceNotFoundException("addressId not found :"+addressId));
        Users   user=usersRepository.findByUserId(userId)
                .orElseThrow(()->new ResourceNotFoundException("user not found :"+userId));
        usersRepository.resetDefaultAddressByUserId(userId);
        address.setIsDefault(true);
        return "default address set successfully";
    }
}

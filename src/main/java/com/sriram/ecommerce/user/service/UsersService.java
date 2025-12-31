package com.sriram.ecommerce.user.service;

import com.sriram.ecommerce.user.domain.UserAddresResponse;
import com.sriram.ecommerce.user.domain.UserAddressDomain;
import com.sriram.ecommerce.user.domain.UserDomain;
import com.sriram.ecommerce.user.enums.UserStatusCode;
import com.sriram.ecommerce.user.exception.ResourceNotFoundException;
import com.sriram.ecommerce.user.model.UserStatus;
import com.sriram.ecommerce.user.model.Users;
import com.sriram.ecommerce.user.projection.UserAddressProjection;
import com.sriram.ecommerce.user.repository.UserStatusRepository;
import com.sriram.ecommerce.user.repository.UsersRepository;
import com.sriram.ecommerce.user.resource.UserAddressResource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserStatusRepository userStatusRepository;

    public ResponseEntity<?> createUser(UserDomain userDomain){

        Users  user=usersRepository.findByEmailIdOrPhoneNumner(userDomain.getEmailId(),userDomain.getPhoneNumner());
        if(user!=null){
            return new ResponseEntity<>("user exits",HttpStatus.OK);
        }else{
            Users users = new Users();
            BeanUtils.copyProperties(userDomain,users,"userId");
            List<UserStatus> statuses = userStatusRepository.findAll();
            UserStatus activeStatus = userStatusRepository
                    .findByStatusCode(UserStatusCode.ACTIVE.name());
            users.setUserStatus(activeStatus);
            users.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            usersRepository.save(users);
            return new ResponseEntity<>("user saved sucessfully",HttpStatus.OK);
        }
    }

    public ResponseEntity<String> userUpdate(UserDomain userDomain) {
        Users   user=usersRepository.findByUserId(userDomain.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("user not found :"+userDomain.getUserId()));
        BeanUtils.copyProperties(userDomain,user);
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        usersRepository.save(user);
        return  new ResponseEntity<>("user updated",HttpStatus.OK);
    }

    public ResponseEntity<UserDomain> fetchUser(Integer userId) {
        Users   user=usersRepository.findByUserId(userId)
                .orElseThrow(()->new ResourceNotFoundException("user not found :"+userId));
        UserDomain userDomain = new UserDomain();
        BeanUtils.copyProperties(user,userDomain);
        return ResponseEntity.ok(userDomain);
    }

    public ResponseEntity<List<UserAddresResponse>> getUsersWthAddress() {
        List<UserAddressProjection>  rows=usersRepository.fetchUsersWithAddress();

        Map<Integer, UserAddresResponse> userMap = new LinkedHashMap<>();

        for (UserAddressProjection row : rows) {

            // create user object once per userId
            UserAddresResponse user =
                    userMap.computeIfAbsent(row.getUserId(), id -> {

                        UserAddresResponse u = new UserAddresResponse();
                        u.setUserId(row.getUserId());
                        u.setFirstName(row.getFirstName());
                        u.setLastName(row.getLastName());
                        u.setEmailId(row.getEmailId());
                        u.setPhoneNumner(row.getPhoneNumber());
                        u.setUserAddressDomainList(new ArrayList<>());
                        return u;
                    });

            // add address only if exists
            if (row.getAddressLine1() != null) {

                UserAddressDomain address = new UserAddressDomain();
                address.setAddressId(row.getAddressId());
                address.setAddressLine1(row.getAddressLine1());
                address.setAddressLine2(row.getAddressLine2());
                address.setCity(row.getCity());
                address.setState(row.getState());
                address.setCountry(row.getCountry());
                address.setPincode(row.getPincode());
                address.setDefault(row.getIsDefault());

                user.getUserAddressDomainList().add(address);
            }
        }
        return ResponseEntity.ok(new ArrayList<>(userMap.values()));
    }

    public ResponseEntity<List<UserAddresResponse>> getUserWthAddress(Integer userId) {
        List<UserAddressProjection>  rows=usersRepository.fetchUserWithAddress(userId);

        Map<Integer, UserAddresResponse> userMap = new LinkedHashMap<>();

        for (UserAddressProjection row : rows) {

            // create user object once per userId
            UserAddresResponse user =
                    userMap.computeIfAbsent(row.getUserId(), id -> {

                        UserAddresResponse u = new UserAddresResponse();
                        u.setUserId(row.getUserId());
                        u.setFirstName(row.getFirstName());
                        u.setLastName(row.getLastName());
                        u.setEmailId(row.getEmailId());
                        u.setPhoneNumner(row.getPhoneNumber());
                        u.setUserAddressDomainList(new ArrayList<>());
                        return u;
                    });

            // add address only if exists
            if (row.getAddressLine1() != null) {

                UserAddressDomain address = new UserAddressDomain();
                address.setAddressId(row.getAddressId());
                address.setAddressLine1(row.getAddressLine1());
                address.setAddressLine2(row.getAddressLine2());
                address.setCity(row.getCity());
                address.setState(row.getState());
                address.setCountry(row.getCountry());
                address.setPincode(row.getPincode());
                address.setDefault(row.getIsDefault());

                user.getUserAddressDomainList().add(address);
            }
        }
        return ResponseEntity.ok(new ArrayList<>(userMap.values()));

    }
}
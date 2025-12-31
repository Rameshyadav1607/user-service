package com.sriram.ecommerce.user.service;

import com.sriram.ecommerce.user.domain.UserDomain;
import com.sriram.ecommerce.user.enums.UserStatusCode;
import com.sriram.ecommerce.user.model.UserStatus;
import com.sriram.ecommerce.user.model.Users;
import com.sriram.ecommerce.user.repository.UserStatusRepository;
import com.sriram.ecommerce.user.repository.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

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
        Users   user=usersRepository.findByUserId(userDomain.getUserId());
        BeanUtils.copyProperties(userDomain,user);
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        usersRepository.save(user);
        return  new ResponseEntity<>("user updated",HttpStatus.OK);
    }

    public ResponseEntity<UserDomain> fetchUser(Integer userId) {
        Users   user=usersRepository.findByUserId(userId);
        UserDomain userDomain = new UserDomain();
        BeanUtils.copyProperties(user,userDomain);
        return ResponseEntity.ok(userDomain);
    }
}
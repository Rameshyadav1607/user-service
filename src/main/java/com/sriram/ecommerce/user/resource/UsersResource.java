package com.sriram.ecommerce.user.resource;

import com.sriram.ecommerce.user.domain.UserAddresResponse;
import com.sriram.ecommerce.user.domain.UserDomain;
import com.sriram.ecommerce.user.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UsersResource {
    @Autowired
    private UsersService usersService;
    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserDomain userDomain){
        return usersService.createUser(userDomain);
    }
    @PutMapping
    public ResponseEntity<String> userUpdate(@RequestBody UserDomain userDomain){
        return usersService.userUpdate(userDomain);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDomain> fetchUser(@PathVariable Integer userId){
        return usersService.fetchUser(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserAddresResponse>> getUsersWthAddress(){
        return usersService.getUsersWthAddress();
    }

    @GetMapping("/userbyid/{userId}")
    public ResponseEntity<List<UserAddresResponse>> getUserWthAddress(@PathVariable Integer userId){
        return usersService.getUserWthAddress(userId);
    }
}

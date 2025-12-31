package com.sriram.ecommerce.user.repository;

import com.sriram.ecommerce.user.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {


    UserStatus findByStatusCode(String name);
}
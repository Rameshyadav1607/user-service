package com.sriram.ecommerce.user.repository;

import com.sriram.ecommerce.user.model.UserRolesId;
import com.sriram.ecommerce.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, UserRolesId> {
    Users findByEmailIdOrPhoneNumner(String emailId, String phoneNumner);

    Users findByUserId(Integer userId);
}
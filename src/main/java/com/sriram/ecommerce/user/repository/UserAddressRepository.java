package com.sriram.ecommerce.user.repository;

import com.sriram.ecommerce.user.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {
}
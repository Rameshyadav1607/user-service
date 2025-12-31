package com.sriram.ecommerce.user.repository;

import com.sriram.ecommerce.user.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
}
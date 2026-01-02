package com.sriram.ecommerce.user.repository;

import com.sriram.ecommerce.user.model.Users;
import com.sriram.ecommerce.user.projection.UserAddressProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUserId(Integer userId);

    @Query(value = """
            select 
            u.user_id as userId,
            u.first_name as firstName,
            u.last_name as lastName,
            ua.address_id as addressId,
            ua.address_line1 as addressLine1,
            ua.address_line2 as addressLine2,
            ua.city as city,
            ua.state as state,
            ua.country as country,
            ua.pincode as pincode,
            ua.is_default as isDefault
            from users u
            left join user_address ua on ua.user_id=u.user_id
            """,nativeQuery = true)
    List<UserAddressProjection> fetchUsersWithAddress();

    @Query(value = """
            select 
            u.user_id as userId,
            u.first_name as firstName,
            u.last_name as lastName,
            ua.address_id as addressId,
            ua.address_line1 as addressLine1,
            ua.address_line2 as addressLine2,
            ua.city as city,
            ua.state as state,
            ua.country as country,
            ua.pincode as pincode,
            ua.is_default as isDefault
            from users u
            left join user_address ua on ua.user_id=u.user_id where u.user_id=:userId
            """,nativeQuery = true)
    List<UserAddressProjection> fetchUserWithAddress(@Param("userId") Integer userId);

    @Modifying
    @Query(value = "update user_address set is_default=false where user_id=:userId",nativeQuery = true)
    void resetDefaultAddressByUserId(@Param("userId") Integer userId);
}
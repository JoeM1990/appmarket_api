package com.monkilatech.appmarket.repository;


//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monkilatech.appmarket.model.User;
 

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {


    @Query("FROM User u WHERE u.id=:id")
    User findById(@Param("id")long id);

    @Query("FROM User u WHERE u.username=:username")
    User findByUsername(@Param("username")String username);

   


}

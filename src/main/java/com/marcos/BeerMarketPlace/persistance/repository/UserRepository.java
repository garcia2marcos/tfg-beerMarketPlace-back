package com.marcos.BeerMarketPlace.persistance.repository;

import com.marcos.BeerMarketPlace.persistance.model.Product;
import com.marcos.BeerMarketPlace.persistance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User ,Long> {

    @Query(value = "select * from users where username=:username && password=:password",nativeQuery = true)
    Long verifyLogIn(String username, String password);

}

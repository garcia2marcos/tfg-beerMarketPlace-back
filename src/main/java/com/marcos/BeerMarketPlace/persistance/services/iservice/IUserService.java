package com.marcos.BeerMarketPlace.persistance.services.iservice;

import com.marcos.BeerMarketPlace.persistance.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);
    Optional<User> update(User user,Long id);

    void remove (Long id);

    Long verifyLogIn(String username, String password);
}

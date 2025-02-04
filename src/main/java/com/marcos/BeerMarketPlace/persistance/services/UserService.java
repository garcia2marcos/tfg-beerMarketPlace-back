package com.marcos.BeerMarketPlace.persistance.services;

import com.marcos.BeerMarketPlace.persistance.model.User;
import com.marcos.BeerMarketPlace.persistance.repository.UserRepository;
import com.marcos.BeerMarketPlace.persistance.services.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> update(User user, Long id) {
        Optional<User> o= this.findById(id);
        if(o.isPresent()){
            User userBdd= o.orElseThrow();
            userBdd.setUsername(user.getUsername());
            userBdd.setEmail(user.getEmail());
            return Optional.of(this.save(userBdd));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void remove(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public Long verifyLogIn(String username, String password) {
        return  userRepository.verifyLogIn(username, password);
    }
}

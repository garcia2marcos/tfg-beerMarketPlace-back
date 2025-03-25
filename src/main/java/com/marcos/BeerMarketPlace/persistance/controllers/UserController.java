package com.marcos.BeerMarketPlace.persistance.controllers;

import com.marcos.BeerMarketPlace.persistance.model.User;
import com.marcos.BeerMarketPlace.persistance.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(originPatterns = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}/{password}")
    public ResponseEntity<?> verifyLogIn(@PathVariable String username, @PathVariable String password) {
        Long logIn = userService.verifyLogIn(username, password);

        if (logIn != null) {
            return ResponseEntity.ok(logIn);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isPresent()){
            return ResponseEntity.ok(userOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){
        if (result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result,@PathVariable Long id){
        if (result.hasErrors()){
            return validation(result);
        }
        Optional<User> o= userService.update(user,id);
        if(o.isPresent()){

            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<User> o= userService.findById(id);

        if(o.isPresent()) {

            userService.remove(id);
            return ResponseEntity.noContent().build();//204
        }

        return ResponseEntity.noContent().build();//204

    }




    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors = new HashMap<>();
        result.getFieldErrors().forEach(er ->{
            errors.put(er.getField(),"El campo "+ er.getField()+" "+er.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }



}

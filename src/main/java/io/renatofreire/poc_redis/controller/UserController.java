package io.renatofreire.poc_redis.controller;

import io.renatofreire.poc_redis.dto.UserDTO;
import io.renatofreire.poc_redis.exception.InvalidBodyException;
import io.renatofreire.poc_redis.model.User;
import io.renatofreire.poc_redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping(path = "{userId}")
    public User getUserById(@PathVariable final Integer userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    public User createNewUser(@RequestBody final UserDTO newUser) throws InvalidBodyException {
        return userService.createNewUser(newUser);
    }

    @PutMapping(path = "{userId}")
    public User updateUser(@PathVariable Integer userId, @RequestBody final User updatedUser) throws InvalidBodyException {
        return userService.updateUser(userId, updatedUser);
    }

    @DeleteMapping(path = "{userId}")
    public boolean deleteUser(@PathVariable Integer userId){
        return userService.deleteUser(userId);
    }

}
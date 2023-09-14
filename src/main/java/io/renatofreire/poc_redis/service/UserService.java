package io.renatofreire.poc_redis.service;

import io.renatofreire.poc_redis.dto.UserDTO;
import io.renatofreire.poc_redis.exception.InvalidBodyException;
import io.renatofreire.poc_redis.model.User;
import io.renatofreire.poc_redis.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "users", key = "#userId")
    public User getUserById(final Integer userId) throws EntityNotFoundException {
        logger.info("Searching in the database by the user with id: " + userId);
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public List<User> getAll(){
        logger.info("Searching in the database for all users");
        return userRepository.findAll(Sort.by("id"));
    }

    public User createNewUser(final UserDTO user) throws InvalidBodyException {
        if(user.name() == null){
            throw new InvalidBodyException("Invalid body");
        }

        User newUser = new User(user);
        userRepository.save(newUser);
        return newUser;
    }

    @CachePut(value = "users",key = "#userId")
    public User updateUser(final Integer userId, final User updatedUser) throws InvalidBodyException {
        User user;

        if(userId == null || updatedUser.getName() == null){
            throw new InvalidBodyException("Invalid body");
        }

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            user = optionalUser.get();
        }else{
            throw new EntityNotFoundException("User with id:" + userId  +  " does not exist");
        }

        user.setName(updatedUser.getName());
        userRepository.save(user);

        return updatedUser;
    }

    @CacheEvict(value = "users", key = "#userId")
    public boolean deleteUser(final Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            userRepository.delete(user.get());
            return true;
        }else{
            throw new EntityNotFoundException("User with id:" + userId +  " does not exist");
        }
    }


}

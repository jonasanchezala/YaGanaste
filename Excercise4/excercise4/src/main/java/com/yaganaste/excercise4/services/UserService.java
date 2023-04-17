package com.yaganaste.excercise4.services;

import com.yaganaste.excercise4.exceptions.UserRetrievedException;
import com.yaganaste.excercise4.models.User;
import com.yaganaste.excercise4.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        try{
            return userRepository.findAll();
        }catch (Exception e){
            throw new UserRetrievedException(e.getMessage());
        }
    }
}
package com.yaganste.web.Login.services;

import com.yaganste.web.Login.dtos.UserCredentialDto;
import com.yaganste.web.Login.exceptions.UserCredentialException;
import com.yaganste.web.Login.repositories.UserCredentialRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialService  {

    private final UserCredentialRepository userCredentialRepository;

    public UserCredentialService(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }

    public final boolean validCredentials(UserCredentialDto userCredentialDto) {
        try{
            return userCredentialRepository.existsUserCredentialByUsernameAndPassword
                    (userCredentialDto.getUsername(), userCredentialDto.getPassword());
        }catch (Exception e){
            throw new UserCredentialException(e.getMessage());
        }
    }
}
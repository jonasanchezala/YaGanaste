package com.yaganste.web.Login.controllers;

import com.yaganste.web.Login.dtos.UserCredentialDto;
import com.yaganste.web.Login.services.UserCredentialService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user-credentials")
public class UserCredentialController {

    private final UserCredentialService userCredentialService;

    public UserCredentialController(UserCredentialService userCredentialService) {
        this.userCredentialService = userCredentialService;
    }

    @CrossOrigin
    @PostMapping(path = "valid",produces = "application/json")
    public boolean validCredentials(@RequestBody UserCredentialDto userCredentialDto) {
        return userCredentialService.validCredentials(userCredentialDto);
    }


}
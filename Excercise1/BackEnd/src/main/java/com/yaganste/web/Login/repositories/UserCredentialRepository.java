package com.yaganste.web.Login.repositories;

import com.yaganste.web.Login.models.UserCredential;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends CrudRepository<UserCredential, Long> {
    boolean existsUserCredentialByUsernameAndPassword(String username, String password);
}
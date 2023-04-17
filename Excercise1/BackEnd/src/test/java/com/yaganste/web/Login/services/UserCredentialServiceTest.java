package com.yaganste.web.Login.services;

import com.yaganste.web.Login.dtos.UserCredentialDto;
import com.yaganste.web.Login.exceptions.UserCredentialException;
import com.yaganste.web.Login.repositories.UserCredentialRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserCredentialServiceTest {

    @Mock
    private UserCredentialRepository userCredentialRepository;

    @InjectMocks
    private UserCredentialService userCredentialService;

    @Test
    void shouldReturnTrueWhenValidCredentialsArePassed() {
        // given
        UserCredentialDto userCredentialDto = UserCredentialDto.builder().username("testuser").password("testpassword").build();
        when(userCredentialRepository.existsUserCredentialByUsernameAndPassword("testuser", "testpassword"))
                .thenReturn(true);

        // when
        boolean result = userCredentialService.validCredentials(userCredentialDto);

        // then
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenInvalidCredentialsArePassed() {
        // given
        UserCredentialDto userCredentialDto = UserCredentialDto.builder().username("testuser").password("testpassword").build();;
        when(userCredentialRepository.existsUserCredentialByUsernameAndPassword("testuser", "testpassword"))
                .thenReturn(false);

        // when
        boolean result = userCredentialService.validCredentials(userCredentialDto);

        // then
        assertFalse(result);
    }

    @Test
    void shouldThrowExceptionWhenRepositoryThrowsException() {
        // given
        UserCredentialDto userCredentialDto = UserCredentialDto.builder().username("testuser").password("testpassword").build();
        when(userCredentialRepository.existsUserCredentialByUsernameAndPassword("testuser", "testpassword"))
                .thenThrow(new RuntimeException("Something went wrong."));

        // when
        UserCredentialException exception = org.junit.jupiter.api.Assertions.assertThrows(UserCredentialException.class,
                () -> userCredentialService.validCredentials(userCredentialDto));

        /// then
        assertTrue(exception.getMessage().contains("Something went wrong."));
    }
}

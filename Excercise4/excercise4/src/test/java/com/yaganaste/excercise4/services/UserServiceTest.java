package com.yaganaste.excercise4.services;

import com.yaganaste.excercise4.exceptions.UserRetrievedException;
import com.yaganaste.excercise4.models.User;
import com.yaganaste.excercise4.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testFindAllUsers() {
        // given
        User user = User.builder().id(1l).name("test").build();
        when(userRepository.findAll())
                .thenReturn(List.of(user));

        // when
        List<User> users = userService.findAllUsers();

        // then
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertTrue(users.stream().allMatch(userFound -> userFound.equals(user)));
    }

    @Test
    void testFindAllUsersWhenExceptionThrown() {
        // given
        when(userRepository.findAll())
                .thenThrow(new RuntimeException("Something went wrong."));

        // when
        UserRetrievedException exception = assertThrows(UserRetrievedException.class,
                () -> userService.findAllUsers());

        // then
        assertTrue(exception.getMessage().contains("Something went wrong."));
    }
}

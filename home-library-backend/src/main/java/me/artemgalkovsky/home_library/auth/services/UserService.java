package me.artemgalkovsky.home_library.auth.services;

import jdk.jshell.spi.ExecutionControl;
import lombok.Data;
import me.artemgalkovsky.home_library.auth.controllers.schemas.LoginCredentials;
import me.artemgalkovsky.home_library.auth.dtos.user_related.UserDto;
import me.artemgalkovsky.home_library.auth.exceptions.FailedRegistrationException;
import me.artemgalkovsky.home_library.auth.exceptions.UserNotFoundException;
import me.artemgalkovsky.home_library.auth.repository.UserRepository;
import me.artemgalkovsky.home_library.auth.repository.entities.user_related.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class UserService {

    private final UserRepository userRepository;

    public boolean registerNewUserIfNotExists(LoginCredentials loginCredentials) throws FailedRegistrationException, ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not implemented now");
    }

    public UserDto getUserByEmail(String email) throws UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User with email '" + email + "' doesn't exists!");
        }

       return UserDto.builder().build();
    }
}

package me.artemgalkovsky.home_library.auth.exceptions;

public class UserNotFoundException extends AuthException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

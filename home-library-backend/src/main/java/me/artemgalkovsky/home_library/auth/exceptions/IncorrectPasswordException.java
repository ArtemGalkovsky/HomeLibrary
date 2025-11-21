package me.artemgalkovsky.home_library.auth.exceptions;

public class IncorrectPasswordException extends AuthException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}

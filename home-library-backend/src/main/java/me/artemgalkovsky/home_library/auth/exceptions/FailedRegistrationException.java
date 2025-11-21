package me.artemgalkovsky.home_library.auth.exceptions;

public class FailedRegistrationException extends AuthException {
    public FailedRegistrationException(String message) {
        super(message);
    }
}

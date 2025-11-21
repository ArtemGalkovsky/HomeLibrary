package me.artemgalkovsky.home_library.auth.exceptions;

public class InvalidRefreshToken extends AuthException {
    public InvalidRefreshToken(String message) {
        super(message);
    }
}

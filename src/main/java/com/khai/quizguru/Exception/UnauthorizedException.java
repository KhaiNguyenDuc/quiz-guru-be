package com.khai.quizguru.Exception;

public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = 1730694003032222533L;

    public UnauthorizedException(String message) {
        super(message);
    }


}

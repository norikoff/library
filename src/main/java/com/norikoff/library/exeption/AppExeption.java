package com.norikoff.library.exeption;

public class AppExeption extends RuntimeException {

    public static Exception create(String msg) {
        return new RuntimeException(msg);
    }
}

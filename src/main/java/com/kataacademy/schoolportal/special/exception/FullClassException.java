package com.kataacademy.schoolportal.special.exception;

public class FullClassException extends RuntimeException {
    public FullClassException (byte classNumber) {
        super("все " + classNumber + " классы заполнены.");
    }
}

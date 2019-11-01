package com.agileengine.exceptions;

public class InvalidSearchCriteria extends Exception {
    public InvalidSearchCriteria() {
    }

    public InvalidSearchCriteria(String message) {
        super(message);
    }

    public InvalidSearchCriteria(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSearchCriteria(Throwable cause) {
        super(cause);
    }

    public InvalidSearchCriteria(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

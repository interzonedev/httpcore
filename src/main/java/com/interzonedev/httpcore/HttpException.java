package com.interzonedev.httpcore;

/**
 * Unchecked exception for HTTP actions.
 */
public class HttpException extends RuntimeException {

    private static final long serialVersionUID = 4915090233972408400L;

    public HttpException() {
        super();
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

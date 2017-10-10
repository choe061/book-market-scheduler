package com.bk.bm.exception;

/**
 * Created by choi on 2017. 10. 10. AM 10:07.
 */
public class ServiceException extends RuntimeException {
    protected int errorCode = -1;
    protected static int CHECKED_EXCEPTION = 99999;

    public ServiceException() {
        super();
    }

    protected ServiceException(String message, int errorCode) {
        super(errorCode + ". " + message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

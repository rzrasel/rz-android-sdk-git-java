package com.rzandjavagit.core.exception;

public class CoreException extends Exception {
    private CoreError.TYPE errorTYPE;
    private String errorMessage = null;

    public CoreException() {
        super();
    }

    public CoreException(CoreError.Reason errorReason) {
        super(errorReason.getErrorDescription());
        errorTYPE = errorReason.getErrorType();
        errorMessage = errorReason.getErrorDescription();
    }

    public CoreException(Throwable argCause) {
        super(argCause);
    }

    public CoreException(CoreError.Reason errorReason, Throwable argCause) {
        super(errorReason.getErrorDescription(), argCause);
        errorTYPE = errorReason.getErrorType();
        errorMessage = errorReason.getErrorDescription();
    }

    @Override
    public String toString() {
        return errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }

    public CoreError.TYPE getErrorType() {
        return errorTYPE;
    }
}

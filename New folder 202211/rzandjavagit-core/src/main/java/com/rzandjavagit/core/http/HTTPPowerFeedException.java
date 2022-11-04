package com.rzandjavagit.core.http;

public class HTTPPowerFeedException extends Exception {
    private String errorMessage = null;

    public HTTPPowerFeedException() {
        super();
    }

    public HTTPPowerFeedException(ErrorReason errorReason) {
        super(errorReason.getErrorDescription());
        errorMessage = errorReason.getErrorDescription();
    }

    public HTTPPowerFeedException(Throwable argCause) {
        super(argCause);
    }

    public HTTPPowerFeedException(ErrorReason errorReason, Throwable argCause) {
        super(errorReason.getErrorDescription(), argCause);
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

    public enum ErrorDescription {
        MALFORMED_URL_EXCEPTION("MALFORMED URL EXCEPTION") {
            @Override
            String onMappedValue(String argParameter) {
                return getMessage() + " - " + argParameter;
            }
        },
        IO_EXCEPTION("IO EXCEPTION") {
            @Override
            String onMappedValue(String argParameter) {
                return getMessage() + " - " + argParameter;
            }
        },
        FILE_NOT_FOUND_EXCEPTION("FILE ONT FOUND EXCEPTION") {
            @Override
            String onMappedValue(String argParameter) {
                return getMessage() + " - " + argParameter;
            }
        },
        FILE_PATH_NOT_EXISTS("FILE PATH ONT EXISTS EXCEPTION") {
            @Override
            String onMappedValue(String argParameter) {
                return getMessage() + " - " + argParameter;
            }
        },
        RESPONSE_ERROR("RESPONSE EXCEPTION") {
            @Override
            String onMappedValue(String argParameter) {
                return getMessage() + " - " + argParameter;
            }
        };

        abstract String onMappedValue(String argParameter);

        private final String itemValue;

        ErrorDescription(String argItemValue) {
            this.itemValue = argItemValue;
        }

        String getMessage() {
            return this.itemValue;
        }
    }
    //http://tutorials.jenkov.com/java/enums.html
    //https://stackoverflow.com/questions/2709593/why-would-an-enum-implement-an-interface
}

class ErrorReason {
    private String errorDescription;

    ErrorReason(HTTPPowerFeedException.ErrorDescription argErrorDescription) {
        this.errorDescription = argErrorDescription.getMessage();
    }

    ErrorReason(HTTPPowerFeedException.ErrorDescription argErrorDescription, String argErrorCause) {
        this.errorDescription = argErrorDescription.onMappedValue(argErrorCause);
    }

    String getErrorDescription() {
        return this.errorDescription;
    }
}
//https://www.baeldung.com/java-new-custom-exception
//https://www.codejava.net/java-core/exception/how-to-create-custom-exceptions-in-java
//https://stackoverflow.com/questions/5819121/understanding-java-ioexception
//https://examples.javacodegeeks.com/core-java/net/malformedurlexception/java-net-malformedurlexception-how-to-solve-malformedurlexception/
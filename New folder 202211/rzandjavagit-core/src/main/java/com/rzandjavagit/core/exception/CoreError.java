package com.rzandjavagit.core.exception;

public class CoreError {
    private static String prefixDescription = "EXCEPTION_OCCUR ";

    public Reason setReason(TYPE argErrorDescription) {
        return new Reason(argErrorDescription);
    }

    public Reason setReason(TYPE argErrorDescription, String argErrorCause) {
        return new Reason(argErrorDescription, argErrorCause);
    }

    public enum TYPE {
        DIRECTORY_ALREADY_EXISTS(prefixDescription + "DIRECTORY EXISTS EXCEPTION") {
            @Override
            String onMappedValue(String argParameter) {
                return getMessage() + " - " + argParameter;
            }
        },
        EXCEPTION(prefixDescription + "EXCEPTION") {
            @Override
            String onMappedValue(String argParameter) {
                return getMessage() + " - " + argParameter;
            }
        },
        FILE_NOT_FOUND_EXCEPTION(prefixDescription + "FILE ONT FOUND EXCEPTION") {
            @Override
            String onMappedValue(String argParameter) {
                return getMessage() + " - " + argParameter;
            }
        },
        FILE_PATH_NOT_EXISTS(prefixDescription + "FILE PATH ONT EXISTS EXCEPTION") {
            @Override
            String onMappedValue(String argParameter) {
                return getMessage() + " - " + argParameter;
            }
        },
        IO_EXCEPTION(prefixDescription + "IO EXCEPTION") {
            @Override
            String onMappedValue(String argParameter) {
                return getMessage() + " - " + argParameter;
            }
        },
        MALFORMED_URL_EXCEPTION(prefixDescription + "MALFORMED URL EXCEPTION") {
            @Override
            String onMappedValue(String argParameter) {
                return getMessage() + " - " + argParameter;
            }
        },
        PERMISSIONS_DENIED(prefixDescription + "PERMISSIONS DENIED EXCEPTION") {
            @Override
            String onMappedValue(String argParameter) {
                return getMessage() + " - " + argParameter;
            }
        },
        RESPONSE_ERROR(prefixDescription + "RESPONSE EXCEPTION") {
            @Override
            String onMappedValue(String argParameter) {
                return getMessage() + " - " + argParameter;
            }
        };

        abstract String onMappedValue(String argParameter);

        private final String itemValue;

        TYPE(String argItemValue) {
            this.itemValue = argItemValue;
        }

        String getMessage() {
            return this.itemValue;
        }
    }

    public class Reason {
        private TYPE errorTYPE;
        private String errorDescription;

        Reason(TYPE argErrorDescription) {
            this.errorTYPE = argErrorDescription;
            this.errorDescription = argErrorDescription.getMessage();
        }

        Reason(TYPE argErrorDescription, String argErrorCause) {
            this.errorTYPE = argErrorDescription;
            this.errorDescription = argErrorDescription.onMappedValue(argErrorCause);
        }

        String getErrorDescription() {
            return this.errorDescription;
        }

        public TYPE getErrorType() {
            return errorTYPE;
        }
    }
}
//http://tutorials.jenkov.com/java/enums.html
//https://stackoverflow.com/questions/2709593/why-would-an-enum-implement-an-interface
//
//
//https://www.baeldung.com/java-new-custom-exception
//https://www.codejava.net/java-core/exception/how-to-create-custom-exceptions-in-java
//https://stackoverflow.com/questions/5819121/understanding-java-ioexception
//https://examples.javacodegeeks.com/core-java/net/malformedurlexception/java-net-malformedurlexception-how-to-solve-malformedurlexception/

//http://www.java67.com/2018/02/10-examples-of-array-in-java-tutorial.html
//https://tutorialseye.com/advanced-array-techniques-java-tutorial.html
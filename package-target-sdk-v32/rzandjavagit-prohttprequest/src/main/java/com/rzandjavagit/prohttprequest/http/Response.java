package com.rzandjavagit.prohttprequest.http;

public class Response {
    private boolean isSuccess;
    private String message;
    private String body;

    public void setIsSuccess(boolean argIsSuccess) {
        this.isSuccess = argIsSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setMessage(String argMessage) {
        this.message = argMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setBody(String argBody) {
        this.body = argBody;
    }

    public String getBody() {
        return body;
    }
}

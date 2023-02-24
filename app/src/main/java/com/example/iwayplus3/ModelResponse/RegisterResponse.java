package com.example.iwayplus3.ModelResponse;

public class RegisterResponse {

    String error;
    String message;
    String exist;

    public RegisterResponse(String error,String message,String exist) {
        this.error = error;
        this.message=message;
        this.exist=exist;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExist() {
        return exist;
    }

    public void setExist(String exist) {
        this.exist = exist;
    }
}

package com.example.iwayplus3.ModelResponse;

public class LoginResponse {
    String error,message;

    public LoginResponse(String error,String message)
    {
        this.error=error;
        this.message=message;

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

}

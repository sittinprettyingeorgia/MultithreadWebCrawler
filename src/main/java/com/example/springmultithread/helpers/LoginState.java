package com.example.springmultithread.helpers;

public class LoginState {

    private boolean loggedIn;
    private String username;
    private String status;

    protected LoginState(){}

    public LoginState(boolean loggedIn, String username, String status){
        this.loggedIn = loggedIn;
        this.username = username;
        this.status = status;
    }
}

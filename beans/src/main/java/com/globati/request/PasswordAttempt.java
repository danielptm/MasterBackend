package com.globati.request;

/**
 * Created by daniel on 12/29/16.
 */
public class PasswordAttempt {
    String username;
    String password;

    public PasswordAttempt(){}

    public PasswordAttempt(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "PasswordAttempt{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

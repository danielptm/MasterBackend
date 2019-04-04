package com.globati.api;

/**
 * Created by daniel on 12/29/16.
 */
public class PasswordAttempt {
    String userName;
    String password;

    public PasswordAttempt(){}

    public PasswordAttempt(String username, String password){
        this.userName = username;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "PasswordAttempt{" +
                "username='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

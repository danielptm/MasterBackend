package com.globati.webmodel;

/**
 * Created by daniel on 1/24/17.
 */
public class BatchPayRequest {

    String username;
    String password;

    public BatchPayRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public BatchPayRequest(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "BatchPayRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
